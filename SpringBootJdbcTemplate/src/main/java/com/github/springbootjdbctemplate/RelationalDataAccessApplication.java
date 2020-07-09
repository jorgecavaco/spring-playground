package com.github.springbootjdbctemplate;

import com.github.springbootjdbctemplate.repository.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RelationalDataAccessApplication implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(RelationalDataAccessApplication.class);

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  CustomerRepository customerRepository;

  public static void main(String args[]) {
    SpringApplication.run(RelationalDataAccessApplication.class, args);
  }

  @Override
  public void run(String... strings) {

    // Split up the array of whole names into an array of first/last names
    List<Object[]> splitUpNames = Stream.of("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
        .map(name -> name.split(" "))
        .collect(Collectors.toList());

    // Uses JdbcTemplate's batchUpdate operation to bulk load data
    customerRepository.createCustomers(splitUpNames);

    log.info("Querying for customer records where first_name = 'Josh':");
    customerRepository.getCustomerByFistName("Josh")
        .forEach(customer -> log.info(customer.toString()));

    log.info("Querying for customer records where first_name = 'Maria':");
    customerRepository.getCustomerByFistName("Maria")
        .forEach(customer -> log.info(customer.toString()));
  }
}