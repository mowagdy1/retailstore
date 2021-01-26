package com.mowagdy.retailstore.core.dto;

import java.util.Date;

public class UserCreationResponse {

    private Long id;
    private Date registeredAt;

    public UserCreationResponse() {
    }

    public UserCreationResponse(Long id, Date registeredAt) {
        this.id = id;
        this.registeredAt = registeredAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }
}
