package com.mowagdy.retailstore.core.factory;

import com.mowagdy.retailstore.core.model.BillItem;
import com.mowagdy.retailstore.core.model.ItemType;
import com.mowagdy.retailstore.core.model.User;

import java.util.Date;
import java.util.List;

public interface BillCalculator {
    Double calculatePercentageBasedDiscount();
}


class AffiliateBillCalculator implements BillCalculator {
    private List<BillItem> items;

    AffiliateBillCalculator(List<BillItem> items) {
        this.items = items;
    }

    @Override
    public Double calculatePercentageBasedDiscount() {
        double discount = 0;
        for (BillItem item : items) {
            if (item.getType() != ItemType.GROCERIES) {
                double priceOfItem = item.getSingleItemPrice() * item.getQuantity();
                discount += priceOfItem * 0.10;
            }
        }
        return discount;
    }
}


class CustomerBillCalculator implements BillCalculator {
    private User user;
    private List<BillItem> items;

    CustomerBillCalculator(User user, List<BillItem> items) {
        this.user = user;
        this.items = items;
    }

    @Override
    public Double calculatePercentageBasedDiscount() {
        double discount = 0;
        for (BillItem item : items) {
            if (item.getType() != ItemType.GROCERIES) {
                double priceOfItem = item.getSingleItemPrice() * item.getQuantity();
                Date twoYearsAgo = new Date(System.currentTimeMillis() - (365L * 24L * 60L * 60L * 1000L));
                double discountOfItem = 0.0;
                if (user.getRegisteredAt().before(twoYearsAgo)) {
                    discountOfItem = priceOfItem * 0.05;
                }
                discount += discountOfItem;
            }
        }
        return discount;
    }
}


class EmployeeBillCalculator implements BillCalculator {
    private List<BillItem> items;

    EmployeeBillCalculator(List<BillItem> items) {
        this.items = items;
    }

    @Override
    public Double calculatePercentageBasedDiscount() {
        double discount = 0;
        for (BillItem item : items) {
            if (item.getType() != ItemType.GROCERIES) {
                double priceOfItem = item.getSingleItemPrice() * item.getQuantity();
                discount += priceOfItem * 0.30;
            }
        }
        return discount;
    }
}
