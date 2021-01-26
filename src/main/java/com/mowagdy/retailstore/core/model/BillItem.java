package com.mowagdy.retailstore.core.model;

import javax.persistence.*;

@Entity
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private ItemType type;

    private String name;

    @Column(name = "single_item_price")
    private double singleItemPrice;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;


    public BillItem() {
    }

    public BillItem(ItemType type, String name, double singleItemPrice, int quantity, Bill bill) {
        this.type = type;
        this.name = name;
        this.singleItemPrice = singleItemPrice;
        this.quantity = quantity;
        this.bill = bill;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getSingleItemPrice() {
        return singleItemPrice;
    }

    public void setSingleItemPrice(double singleItemPrice) {
        this.singleItemPrice = singleItemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
