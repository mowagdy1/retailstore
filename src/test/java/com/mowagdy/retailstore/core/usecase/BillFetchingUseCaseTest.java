package com.mowagdy.retailstore.core.usecase;

import com.mowagdy.retailstore.core.dto.BillFetchingResponse;
import com.mowagdy.retailstore.core.dto.RequestWithLongId;
import com.mowagdy.retailstore.core.exception.FieldRequiredException;
import com.mowagdy.retailstore.core.model.Bill;
import com.mowagdy.retailstore.core.model.User;
import com.mowagdy.retailstore.core.model.UserType;
import com.mowagdy.retailstore.infrastructure.repo.BillRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class BillFetchingUseCaseTest {

    private BillRepository billRepository = Mockito.mock(BillRepository.class);

    @Test
    void fetchingBillWithMissingNameThrowsException() {

        RequestWithLongId request = new RequestWithLongId(0L);

        BillFetchingUseCase billFetchingUseCase = new BillFetchingUseCase(request, billRepository);

        try {
            billFetchingUseCase.execute();
        } catch (FieldRequiredException e) {
            assertThat(e.getArguments().get("field")).isEqualTo("id");
        }
    }

    @Test
    void fetchingBillWithValidDataWorks() {

        User mockedUser = new User("Mo", UserType.EMPLOYEE, new Date());
        mockedUser.setId(1L);
        Bill mockedBill = new Bill(mockedUser);
        mockedBill.setId(2L);
        mockedBill.setItems(new ArrayList<>());
        Mockito.when(billRepository.findById(2L)).thenReturn(Optional.of(mockedBill));

        RequestWithLongId request = new RequestWithLongId(2L);

        BillFetchingResponse response = new BillFetchingUseCase(request, billRepository).execute();

        assertThat(response.getId()).isEqualTo(2L);
    }

}
