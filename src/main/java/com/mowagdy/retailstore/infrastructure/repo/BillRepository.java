package com.mowagdy.retailstore.infrastructure.repo;

import com.mowagdy.retailstore.core.model.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
}
