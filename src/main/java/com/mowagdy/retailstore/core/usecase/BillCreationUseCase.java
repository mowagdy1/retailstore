package com.mowagdy.retailstore.core.usecase;

import com.mowagdy.retailstore.core.dto.BillCreationRequest;
import com.mowagdy.retailstore.core.dto.BillCreationRequestItem;
import com.mowagdy.retailstore.core.dto.BillCreationResponse;
import com.mowagdy.retailstore.core.exception.ResourceNotFoundException;
import com.mowagdy.retailstore.core.factory.BillCalculatorFactory;
import com.mowagdy.retailstore.core.model.Bill;
import com.mowagdy.retailstore.core.model.BillItem;
import com.mowagdy.retailstore.core.model.ItemType;
import com.mowagdy.retailstore.core.model.User;
import com.mowagdy.retailstore.infrastructure.repo.BillRepository;
import com.mowagdy.retailstore.infrastructure.repo.UserRepository;
import com.mowagdy.retailstore.core.validator.FieldRequiredValidator;

import java.util.ArrayList;
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
        new FieldRequiredValidator<>(request.getUserId(), "userId").validateOrThrow();
        for (BillCreationRequestItem item : request.getItems()) {
            new FieldRequiredValidator<>(item.getType(), "item.type").validateOrThrow();
            new FieldRequiredValidator<>(item.getName(), "item.name").validateOrThrow();
            new FieldRequiredValidator<>(item.getSingleItemPrice(), "item.singleItemPrice").validateOrThrow();
            new FieldRequiredValidator<>(item.getQuantity(), "item.quantity").validateOrThrow();
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

        BillCalculatorFactory factory = new BillCalculatorFactory(user, items);
        double percentageBasedDiscount = factory.getBillCalculator().calculatePercentageBasedDiscount();

        long valueBasedDiscount = this.calculateValueBasedDiscount();

        return totalPrice - (percentageBasedDiscount + valueBasedDiscount);
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
