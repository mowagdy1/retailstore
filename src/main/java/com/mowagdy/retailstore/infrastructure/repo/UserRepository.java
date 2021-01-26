package com.mowagdy.retailstore.infrastructure.repo;

import com.mowagdy.retailstore.core.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
