package com.mowagdy.retailstore.core.dto;

import com.mowagdy.retailstore.core.model.ItemType;

public class BillCreationRequestItem {

    private ItemType type = ItemType.NONE;
    private String name = "";
    private Double singleItemPrice = 0.0;
    private Integer quantity = 0;

    public BillCreationRequestItem() {
    }

    public BillCreationRequestItem(ItemType type, String name, Double singleItemPrice, Integer quantity) {
        this.type = type;
        this.name = name;
        this.singleItemPrice = singleItemPrice;
        this.quantity = quantity;
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
