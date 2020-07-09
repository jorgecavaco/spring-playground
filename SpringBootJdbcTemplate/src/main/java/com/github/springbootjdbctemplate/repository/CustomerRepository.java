package com.github.springbootjdbctemplate.repository;

import com.github.springbootjdbctemplate.entity.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public CustomerRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void createCustomers(List<Object[]> splitUpNames) {
    jdbcTemplate
        .batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
  }

  public List<Customer> getCustomerByFistName(String firstName) {

    return jdbcTemplate.query(
        "SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
        new Object[]{firstName}, new CustomerMapper()
    );
  }

}
