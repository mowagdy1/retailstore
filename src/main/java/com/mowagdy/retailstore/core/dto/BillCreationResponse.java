package com.mowagdy.retailstore.core.dto;

import java.util.Date;

public class BillCreationResponse {

    private Long id;
    private Double totalPrice;
    private Double netPayable;
    private Date createdAt;

    public BillCreationResponse() {
    }

    public BillCreationResponse(Long id, Double totalPrice, Double netPayable, Date createdAt) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.netPayable = netPayable;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
