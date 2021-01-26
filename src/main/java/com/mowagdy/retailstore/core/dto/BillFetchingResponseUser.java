package com.mowagdy.retailstore.core.dto;

import com.mowagdy.retailstore.core.model.User;
import com.mowagdy.retailstore.core.model.UserType;

import java.util.Date;

public class BillFetchingResponseUser {

    private Long id;
    private String name;
    private UserType userType;
    private Date registeredAt;

    public BillFetchingResponseUser() {
    }

    public BillFetchingResponseUser(Long id, String name, UserType userType, Date registeredAt) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.registeredAt = registeredAt;
    }

    public BillFetchingResponseUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userType = user.getUserType();
        this.registeredAt = user.getRegisteredAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }
}
