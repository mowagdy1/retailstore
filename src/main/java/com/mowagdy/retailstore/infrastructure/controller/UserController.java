package com.mowagdy.retailstore.infrastructure.controller;

import com.mowagdy.retailstore.core.dto.UserCreationRequest;
import com.mowagdy.retailstore.core.dto.UserCreationResponse;
import com.mowagdy.retailstore.infrastructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public @ResponseBody
    UserCreationResponse addUser(@RequestBody(required = true) UserCreationRequest request) {
        return userService.addUser(request);
    }

}
