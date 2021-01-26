package com.mowagdy.retailstore.core.dto;

import com.mowagdy.retailstore.core.model.UserType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserCreationRequest {

    private String name = "";
    private UserType userType = UserType.NONE;
    private @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    Date registeredAt = new Date();

    public UserCreationRequest() {
    }

    public UserCreationRequest(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
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
