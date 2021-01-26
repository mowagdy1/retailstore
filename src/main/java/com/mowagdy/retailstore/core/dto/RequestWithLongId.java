package com.mowagdy.retailstore.core.dto;

public class RequestWithLongId {

    private Long id = 0L;

    public RequestWithLongId() {
    }

    public RequestWithLongId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
