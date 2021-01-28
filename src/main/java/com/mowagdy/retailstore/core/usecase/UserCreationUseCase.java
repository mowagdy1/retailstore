package com.mowagdy.retailstore.core.usecase;

import com.mowagdy.retailstore.core.dto.UserCreationRequest;
import com.mowagdy.retailstore.core.dto.UserCreationResponse;
import com.mowagdy.retailstore.core.model.User;
import com.mowagdy.retailstore.infrastructure.repo.UserRepository;
import com.mowagdy.retailstore.core.validator.FieldRequiredValidator;

public class UserCreationUseCase extends BaseUseCase<UserCreationResponse> {

    private final UserCreationRequest request;
    private final UserRepository userRepository;

    public UserCreationUseCase(UserCreationRequest userCreationRequest, UserRepository userRepository) {
        this.request = userCreationRequest;
        this.userRepository = userRepository;
    }

    @Override
    void validate() {
        new FieldRequiredValidator<>(request.getName(), "name").validateOrThrow();
        new FieldRequiredValidator<>(request.getUserType(), "userType").validateOrThrow();
    }

    @Override
    public UserCreationResponse process() {

        User user = new User(request.getName(), request.getUserType(), request.getRegisteredAt());

        User savedUser = userRepository.save(user);

        return new UserCreationResponse(savedUser.getId(), savedUser.getRegisteredAt());
    }
}
