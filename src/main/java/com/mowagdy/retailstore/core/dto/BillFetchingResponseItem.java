package com.mowagdy.retailstore.core.dto;

import com.mowagdy.retailstore.core.model.BillItem;
import com.mowagdy.retailstore.core.model.ItemType;

public class BillFetchingResponseItem {

    private Long id;
    private ItemType type;
    private String name;
    private Double singleItemPrice;
    private Integer quantity;

    public BillFetchingResponseItem() {
    }

    public BillFetchingResponseItem(Long id, ItemType type, String name, Double singleItemPrice, Integer quantity) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.singleItemPrice = singleItemPrice;
        this.quantity = quantity;
    }

    public BillFetchingResponseItem(BillItem item) {
        this.id = item.getId();
        this.type = item.getType();
        this.name = item.getName();
        this.singleItemPrice = item.getSingleItemPrice();
        this.quantity = item.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSingleItemPrice() {
        return singleItemPrice;
    }

    public void setSingleItemPrice(Double singleItemPrice) {
        this.singleItemPrice = singleItemPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
