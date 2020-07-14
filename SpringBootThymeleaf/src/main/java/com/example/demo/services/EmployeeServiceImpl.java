package com.example.demo.services;

import com.example.demo.EmployeeRepository;
import com.example.demo.entity.EmployeeEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<EmployeeEntity> getEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public void delete(Long id) {
    employeeRepository.delete(employeeRepository.getOne(id));
  }

  @Override
  public EmployeeEntity getEmployeeById(Long id) {
    return employeeRepository.getOne(id);
  }

  @Override
  public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) {
    if (entity.getId() == null) {
      entity = employeeRepository.save(entity);

      return entity;
    } else {
      Optional<EmployeeEntity> employee = employeeRepository.findById(entity.getId());

      if (employee.isPresent()) {
        EmployeeEntity newEntity = employee.get();
        newEntity.setEmail(entity.getEmail());
        newEntity.setFirstName(entity.getFirstName());
        newEntity.setLastName(entity.getLastName());

        newEntity = employeeRepository.save(newEntity);

        return newEntity;
      } else {
        entity = employeeRepository.save(entity);

        return entity;
      }
    }
  }
}
