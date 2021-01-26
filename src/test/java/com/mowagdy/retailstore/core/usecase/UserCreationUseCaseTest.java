package com.mowagdy.retailstore.core.usecase;

import com.mowagdy.retailstore.core.dto.UserCreationRequest;
import com.mowagdy.retailstore.core.dto.UserCreationResponse;
import com.mowagdy.retailstore.core.exception.FieldRequiredException;
import com.mowagdy.retailstore.core.model.User;
import com.mowagdy.retailstore.core.model.UserType;
import com.mowagdy.retailstore.infrastructure.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class UserCreationUseCaseTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    void creatingUserWithMissingNameThrowsException() {

        UserCreationRequest request = new UserCreationRequest("", UserType.EMPLOYEE);

        UserCreationUseCase billCreationUseCase = new UserCreationUseCase(request, userRepository);

        try {
            billCreationUseCase.execute();
        } catch (FieldRequiredException e) {
            assertThat(e.getArguments().get("field")).isEqualTo("name");
        }
    }

    @Test
    void creatingUserWithMissingUserTypeThrowsException() {

        UserCreationRequest request = new UserCreationRequest("Mo", UserType.NONE);

        UserCreationUseCase billCreationUseCase = new UserCreationUseCase(request, userRepository);

        try {
            billCreationUseCase.execute();
        } catch (FieldRequiredException e) {
            assertThat(e.getArguments().get("field")).isEqualTo("userType");
        }
    }

    @Test
    void creatingUserWithValidDataWorks() {

        User mockedUser = new User("Mo", UserType.AFFILIATE, new Date());
        mockedUser.setId(1L);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockedUser);

        UserCreationRequest request = new UserCreationRequest("Mo", UserType.AFFILIATE);

        UserCreationResponse response = new UserCreationUseCase(request, userRepository).execute();

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getRegisteredAt()).isEqualTo(mockedUser.getRegisteredAt());
    }

}
