package com.github.springbootjdbctemplate.repository;

import com.github.springbootjdbctemplate.entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {

  @Override
  public Customer mapRow(ResultSet rs, int i) throws SQLException {
    return new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"));
  }
}
