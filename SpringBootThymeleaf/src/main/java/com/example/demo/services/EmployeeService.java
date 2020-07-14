package com.example.demo.services;

import com.example.demo.entity.EmployeeEntity;
import java.util.List;

public interface EmployeeService {

  List<EmployeeEntity> getEmployees();

  void delete(Long id);

  EmployeeEntity getEmployeeById(Long id);

  EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity);
}
