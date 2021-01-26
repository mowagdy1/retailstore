package com.mowagdy.retailstore.core.usecase;

import com.mowagdy.retailstore.core.dto.BillCreationRequest;
import com.mowagdy.retailstore.core.dto.BillCreationRequestItem;
import com.mowagdy.retailstore.core.dto.BillCreationResponse;
import com.mowagdy.retailstore.core.exception.FieldRequiredException;
import com.mowagdy.retailstore.core.model.ItemType;
import com.mowagdy.retailstore.core.model.User;
import com.mowagdy.retailstore.core.model.UserType;
import com.mowagdy.retailstore.infrastructure.repo.BillRepository;
import com.mowagdy.retailstore.infrastructure.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class BillCreationUseCaseTest {

    private BillRepository billRepository = Mockito.mock(BillRepository.class);
    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    void creatingBillWithMissingUserIdThrowsException() {

        BillCreationRequest request = new BillCreationRequest();

        BillCreationUseCase billCreationUseCase = new BillCreationUseCase(request, userRepository, billRepository);

        try {
            billCreationUseCase.execute();
        } catch (FieldRequiredException e) {
            assertThat(e.getArguments().get("field")).isEqualTo("userId");
        }
    }

    @Test
    void creatingBillWithMissingItemTypeThrowsException() {

        List<BillCreationRequestItem> billItems = new ArrayList<>();
        billItems.add(new BillCreationRequestItem(ItemType.NONE, "Bread", 10.50, 2));

        BillCreationRequest request = new BillCreationRequest(4L, billItems);

        BillCreationUseCase billCreationUseCase = new BillCreationUseCase(request, userRepository, billRepository);

        try {
            billCreationUseCase.execute();
        } catch (FieldRequiredException e) {
            assertThat(e.getArguments().get("field")).isEqualTo("item.type");
        }
    }

    @Test
    void creatingBillWithMissingItemNameThrowsException() {

        List<BillCreationRequestItem> billItems = new ArrayList<>();
        billItems.add(new BillCreationRequestItem(ItemType.BAKERY, "", 10.50, 2));

        BillCreationRequest request = new BillCreationRequest(4L, billItems);

        BillCreationUseCase billCreationUseCase = new BillCreationUseCase(request, userRepository, billRepository);

        try {
            billCreationUseCase.execute();
        } catch (FieldRequiredException e) {
            assertThat(e.getArguments().get("field")).isEqualTo("item.name");
        }
    }

    @Test
    void creatingBillWithMissingItemPriceThrowsException() {

        List<BillCreationRequestItem> billItems = new ArrayList<>();
        billItems.add(new BillCreationRequestItem(ItemType.BAKERY, "Bread", 0.0, 2));

        BillCreationRequest request = new BillCreationRequest(4L, billItems);

        BillCreationUseCase billCreationUseCase = new BillCreationUseCase(request, userRepository, billRepository);

        try {
            billCreationUseCase.execute();
        } catch (FieldRequiredException e) {
            assertThat(e.getArguments().get("field")).isEqualTo("item.singleItemPrice");
        }
    }

    @Test
    void creatingBillWithMissingItemQuantityThrowsException() {

        List<BillCreationRequestItem> billItems = new ArrayList<>();
        billItems.add(new BillCreationRequestItem(ItemType.BAKERY, "Bread", 10.50, 0));

        BillCreationRequest request = new BillCreationRequest(4L, billItems);

        BillCreationUseCase billCreationUseCase = new BillCreationUseCase(request, userRepository, billRepository);

        try {
            billCreationUseCase.execute();
        } catch (FieldRequiredException e) {
            assertThat(e.getArguments().get("field")).isEqualTo("item.quantity");
        }
    }


    @Test
    void creatingBillWithValidDataForEmployeeAndGroceryItemWorks() {

        User mockedUser = new User("Mo", UserType.EMPLOYEE, new Date());
        when(userRepository.findById(4L)).thenReturn(Optional.of(mockedUser));

        List<BillCreationRequestItem> billItems = new ArrayList<>();
        billItems.add(new BillCreationRequestItem(ItemType.GROCERIES, "Tide", 10.50, 2));

        BillCreationRequest request = new BillCreationRequest(4L, billItems);

        BillCreationResponse response = new BillCreationUseCase(request, userRepository, billRepository).execute();

        assertThat(response.getTotalPrice()).isEqualTo(21.0);
        assertThat(response.getNetPayable()).isEqualTo(21.0);
    }

    @Test
    void creatingBillWithValidDataForEmployeeAndBakeryItemWorks() {

        User mockedUser = new User("Mo", UserType.EMPLOYEE, new Date());
        when(userRepository.findById(4L)).thenReturn(Optional.of(mockedUser));

        List<BillCreationRequestItem> billItems = new ArrayList<>();
        billItems.add(new BillCreationRequestItem(ItemType.BAKERY, "Bread", 10.50, 2));

        BillCreationRequest request = new BillCreationRequest(4L, billItems);

        BillCreationResponse response = new BillCreationUseCase(request, userRepository, billRepository).execute();

        assertThat(response.getTotalPrice()).isEqualTo(21.0);
        assertThat(response.getNetPayable()).isEqualTo(14.7);
    }

    @Test
    void creatingBillWithValidDataForAffiliateAndBakeryItemWorks() {

        User mockedUser = new User("Mo", UserType.AFFILIATE, new Date());
        when(userRepository.findById(4L)).thenReturn(Optional.of(mockedUser));

        List<BillCreationRequestItem> billItems = new ArrayList<>();
        billItems.add(new BillCreationRequestItem(ItemType.BAKERY, "Bread", 10.50, 2));

        BillCreationRequest request = new BillCreationRequest(4L, billItems);

        BillCreationResponse response = new BillCreationUseCase(request, userRepository, billRepository).execute();

        assertThat(response.getTotalPrice()).isEqualTo(21.0);
        assertThat(response.getNetPayable()).isEqualTo(18.9);
    }

    @Test
    void creatingBillWithValidDataForCustomerForOverTwoYearsAndBakeryItemWorks() {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -3);
        Date threeYearsAgo = cal.getTime();

        User mockedUser = new User("Mo", UserType.CUSTOMER, threeYearsAgo);
        when(userRepository.findById(4L)).thenReturn(Optional.of(mockedUser));

        List<BillCreationRequestItem> billItems = new ArrayList<>();
        billItems.add(new BillCreationRequestItem(ItemType.BAKERY, "Bread", 10.50, 2));

        BillCreationRequest request = new BillCreationRequest(4L, billItems);

        BillCreationResponse response = new BillCreationUseCase(request, userRepository, billRepository).execute();

        assertThat(response.getTotalPrice()).isEqualTo(21.0);
        assertThat(response.getNetPayable()).isEqualTo(19.95);
    }

}
