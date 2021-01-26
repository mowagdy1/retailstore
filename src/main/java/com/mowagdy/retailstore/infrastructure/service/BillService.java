package com.mowagdy.retailstore.infrastructure.service;

import com.mowagdy.retailstore.core.dto.BillCreationRequest;
import com.mowagdy.retailstore.core.dto.BillCreationResponse;
import com.mowagdy.retailstore.core.dto.BillFetchingResponse;
import com.mowagdy.retailstore.core.dto.RequestWithLongId;
import com.mowagdy.retailstore.infrastructure.repo.BillRepository;
import com.mowagdy.retailstore.infrastructure.repo.UserRepository;
import com.mowagdy.retailstore.core.usecase.BillCreationUseCase;
import com.mowagdy.retailstore.core.usecase.BillFetchingUseCase;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    //    @Autowired
    private final UserRepository userRepository;
    private final BillRepository billRepository;

    public BillService(UserRepository userRepository, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.billRepository = billRepository;
    }

    public BillCreationResponse addBill(BillCreationRequest billCreationRequest) {
        return new BillCreationUseCase(billCreationRequest, userRepository, billRepository).execute();
    }

    public BillFetchingResponse getBill(RequestWithLongId requestWithLongId) {
        return new BillFetchingUseCase(requestWithLongId, billRepository).execute();
    }

}
