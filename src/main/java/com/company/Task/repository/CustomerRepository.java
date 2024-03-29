package com.company.Task.repository;

import com.company.Task.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByCustomerIdAndDeletedAtIsNull(Long customerId);
}