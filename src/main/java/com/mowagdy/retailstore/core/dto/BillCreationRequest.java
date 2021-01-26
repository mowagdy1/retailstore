package com.mowagdy.retailstore.core.dto;

import java.util.ArrayList;
import java.util.List;

public class BillCreationRequest {

    private Long userId = 0L;
    private List<BillCreationRequestItem> items = new ArrayList<>();

    public BillCreationRequest() {
    }

    public BillCreationRequest(Long userId, List<BillCreationRequestItem> items) {
        this.userId = userId;
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<BillCreationRequestItem> getItems() {
        return items;
    }

    public void setItems(List<BillCreationRequestItem> items) {
        this.items = items;
    }
}
