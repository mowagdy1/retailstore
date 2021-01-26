package com.mowagdy.retailstore.core.usecase;

import com.mowagdy.retailstore.core.dto.BillCreationRequest;
import com.mowagdy.retailstore.core.dto.BillCreationRequestItem;
import com.mowagdy.retailstore.core.dto.BillCreationResponse;
import com.mowagdy.retailstore.core.exception.ResourceNotFoundException;
import com.mowagdy.retailstore.core.model.Bill;
import com.mowagdy.retailstore.core.model.BillItem;
import com.mowagdy.retailstore.core.model.ItemType;
import com.mowagdy.retailstore.core.model.User;
import com.mowagdy.retailstore.infrastructure.repo.BillRepository;
import com.mowagdy.retailstore.infrastructure.repo.UserRepository;
import com.mowagdy.retailstore.core.validator.FieldRequiredValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillCreationUseCase extends BaseUseCase<BillCreationResponse> {

    private final BillCreationRequest request;
    private final UserRepository userRepository;
    private final BillRepository billRepository;

    public BillCreationUseCase(BillCreationRequest billCreationRequest, UserRepository userRepository, BillRepository billRepository) {
        this.request = billCreationRequest;
        this.userRepository = userRepository;
        this.billRepository = billRepository;
    }

    @Override
    void validate() {
        new FieldRequiredValidator<>(request.getUserId(), "userId").validate();
        for (BillCreationRequestItem item : request.getItems()) {
            new FieldRequiredValidator<>(item.getType(), "item.type").validate();
            new FieldRequiredValidator<>(item.getName(), "item.name").validate();
            new FieldRequiredValidator<>(item.getSingleItemPrice(), "item.singleItemPrice").validate();
            new FieldRequiredValidator<>(item.getQuantity(), "item.quantity").validate();
        }
    }

    @Override
    BillCreationResponse process() {

        User user = userRepository.findById(request.getUserId()).orElseThrow(ResourceNotFoundException::new);

        Bill bill = new Bill(user);
        addingItems(bill);

        double totalPrice = calculateTotalPrice();
        bill.setTotalPrice(totalPrice);

        double netPayable = calculateNetPayable(user, bill.getItems(), totalPrice);
        bill.setNetPayable(netPayable);

        billRepository.save(bill);

        return new BillCreationResponse(bill.getId(), bill.getTotalPrice(), bill.getNetPayable(), bill.getCreatedAt());
    }

    private void addingItems(Bill bill) {
        List<BillItem> items = new ArrayList<>();
        for (BillCreationRequestItem item : request.getItems()) {
            items.add(new BillItem(item.getType(), item.getName(), item.getSingleItemPrice(), item.getQuantity(), bill));
        }
        bill.setItems(items);
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (BillCreationRequestItem item : request.getItems()) {
            totalPrice += (item.getQuantity() * item.getSingleItemPrice());
        }
        return totalPrice;
    }

    private double calculateNetPayable(User user, List<BillItem> items, Double totalPrice) {

        double percentageBasedDiscount = this.calculatePercentageBasedDiscount(user, items);
        long valueBasedDiscount = this.calculateValueBasedDiscount();

        return totalPrice - (percentageBasedDiscount + valueBasedDiscount);
    }

    private double calculatePercentageBasedDiscount(User user, List<BillItem> items) {

        double discount = 0;

        for (BillItem item : items) {
            if (item.getType() != ItemType.GROCERIES) {

                double priceOfItem = item.getSingleItemPrice() * item.getQuantity();
                double discountOfItem = 0.0;

                switch (user.getUserType()) {
                    case EMPLOYEE:
                        discountOfItem = priceOfItem * 0.30;
                        break;
                    case AFFILIATE:
                        discountOfItem = priceOfItem * 0.10;
                        break;
                    case CUSTOMER: {
                        Date twoYearsAgo = new Date(System.currentTimeMillis() - (365L * 24L * 60L * 60L * 1000L));
                        if (user.getRegisteredAt().before(twoYearsAgo)) {
                            discountOfItem = priceOfItem * 0.05;
                        }
                        break;
                    }
                    default:
                        discountOfItem = 0;
                }

                discount += discountOfItem;
            }
        }
        return discount;
    }

    private long calculateValueBasedDiscount() {
        double totalNotGroceriesItems = 0;
        for (BillCreationRequestItem item : request.getItems()) {
            if (item.getType() != ItemType.GROCERIES) {
                totalNotGroceriesItems += (item.getQuantity() * item.getSingleItemPrice());
            }
        }
        return ((long) totalNotGroceriesItems / 100) * 5;
    }

}
