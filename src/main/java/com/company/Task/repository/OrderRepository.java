package com.company.Task.repository;

import com.company.Task.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByCustomer_CustomerId_AndCustomer_DeletedAtIsNull(Long customerId);
}