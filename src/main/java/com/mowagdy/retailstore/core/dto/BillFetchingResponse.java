package com.mowagdy.retailstore.core.dto;

import com.mowagdy.retailstore.core.model.Bill;
import com.mowagdy.retailstore.core.model.BillItem;
import com.mowagdy.retailstore.core.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillFetchingResponse {

    private Long id;
    private BillFetchingResponseUser user;
    private List<BillFetchingResponseItem> items;
    private Double totalPrice;
    private Double netPayable;
    private Date createdAt;

    public BillFetchingResponse() {
    }

    public BillFetchingResponse(Long id, User user, List<BillItem> items, Double netPayable, Date createdAt) {
        this.id = id;
        this.user = new BillFetchingResponseUser(user);
        setItems(items);
        this.netPayable = netPayable;
        this.createdAt = createdAt;
    }

    public BillFetchingResponse(Bill bill) {
        this.id = bill.getId();
        this.user = new BillFetchingResponseUser(bill.getUser());
        setItems(bill.getItems());
        this.totalPrice = bill.getTotalPrice();
        this.netPayable = bill.getNetPayable();
        this.createdAt = bill.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BillFetchingResponseUser getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = new BillFetchingResponseUser(user);
    }

    public List<BillFetchingResponseItem> getItems() {
        return items;
    }

    public void setItems(List<BillItem> items) {
        List<BillFetchingResponseItem> mappedItems = new ArrayList<>();
        for (BillItem item : items) {
            mappedItems.add(new BillFetchingResponseItem(item.getId(), item.getType(), item.getName(), item.getSingleItemPrice(), item.getQuantity()));
        }
        this.items = mappedItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getNetPayable() {
        return netPayable;
    }

    public void setNetPayable(Double netPayable) {
        this.netPayable = netPayable;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
