package com.mowagdy.retailstore.infrastructure.service;

import com.mowagdy.retailstore.core.dto.UserCreationRequest;
import com.mowagdy.retailstore.core.dto.UserCreationResponse;
import com.mowagdy.retailstore.infrastructure.repo.UserRepository;
import com.mowagdy.retailstore.core.usecase.UserCreationUseCase;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreationResponse addUser(UserCreationRequest userCreationRequest) {
        return new UserCreationUseCase(userCreationRequest, userRepository).execute();
    }

}
