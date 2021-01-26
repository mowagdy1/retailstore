package com.mowagdy.retailstore.infrastructure.repo;

import com.mowagdy.retailstore.core.model.BillItem;
import org.springframework.data.repository.CrudRepository;

public interface BillItemRepository extends CrudRepository<BillItem, Long> {
}
