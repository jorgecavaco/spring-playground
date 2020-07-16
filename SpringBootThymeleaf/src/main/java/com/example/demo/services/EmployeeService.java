package com.example.demo.services;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {

  List<Employee> getEmployees();

  void delete(Long id);

  Employee getEmployeeById(Long id);

  Employee createOrUpdateEmployee(Employee entity);
}
