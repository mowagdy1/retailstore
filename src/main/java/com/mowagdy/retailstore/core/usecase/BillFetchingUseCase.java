package com.mowagdy.retailstore.core.usecase;

import com.mowagdy.retailstore.core.dto.RequestWithLongId;
import com.mowagdy.retailstore.core.dto.BillFetchingResponse;
import com.mowagdy.retailstore.core.exception.ResourceNotFoundException;
import com.mowagdy.retailstore.core.model.Bill;
import com.mowagdy.retailstore.infrastructure.repo.BillRepository;
import com.mowagdy.retailstore.core.validator.FieldRequiredValidator;

public class BillFetchingUseCase extends BaseUseCase<BillFetchingResponse> {

    private final RequestWithLongId request;
    private final BillRepository billRepository;

    public BillFetchingUseCase(RequestWithLongId request, BillRepository billRepository) {
        this.request = request;
        this.billRepository = billRepository;
    }

    @Override
    void validate() {
        new FieldRequiredValidator<>(request.getId(), "id").validateOrThrow();
    }

    @Override
    BillFetchingResponse process() {

        Bill bill = billRepository.findById(request.getId()).orElseThrow(ResourceNotFoundException::new);

        return new BillFetchingResponse(bill);
    }
}
