package com.github.springexercise.repository;

import com.github.springexercise.model.Customer;
import com.github.springexercise.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findByCustomer(Customer customer);
}
