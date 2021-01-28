package com.mowagdy.retailstore.core.factory;

import com.mowagdy.retailstore.core.model.BillItem;
import com.mowagdy.retailstore.core.model.User;

import java.util.List;

public class BillCalculatorFactory {
    private User user;
    private List<BillItem> items;

    public BillCalculatorFactory(User user, List<BillItem> items) {
        this.user = user;
        this.items = items;
    }

    public BillCalculator getBillCalculator() {
        switch (user.getUserType()) {
            case EMPLOYEE:
                return new EmployeeBillCalculator(items);
            case CUSTOMER:
                return new CustomerBillCalculator(user, items);
            case AFFILIATE:
                return new AffiliateBillCalculator(items);
        }
        return null;
    }
}
