package com.example.demo.services;

import static java.util.stream.Collectors.toList;

import com.example.demo.EmployeeRepository;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.model.Employee;
import java.util.List;
import java.util.Optional;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> getEmployees() {
    return employeeRepository.findAll().stream().map(Employee::from).collect(toList());
  }

  @Override
  public void delete(Long id) {
    employeeRepository.delete(employeeRepository.getOne(id));
  }

  @Override
  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  //@RolesAllowed("ROLE_USER")
  public Employee getEmployeeById(Long id) {
    return Employee.from(employeeRepository.getOne(id));
  }

  @Override
  //@PreAuthorize("hasRole('ROLE_USER')")
  //@PreAuthorize("#entity.FirstName != authentication.principal.username")
  //@PostAuthorize("returnObject.firstName != authentication.principal.username")
  public Employee createOrUpdateEmployee(Employee entity) {
    if (entity.getId() == null) {
      return Employee.from(employeeRepository.save(entity.copy(new EmployeeEntity())));
    } else {
      Optional<EmployeeEntity> employee = employeeRepository.findById(entity.getId());

      return employee.map(
          employeeEntity -> Employee.from(employeeRepository.save(entity.copy(employeeEntity))))
          .orElseGet(
              () -> Employee.from(employeeRepository.save(entity.copy(new EmployeeEntity()))));
    }
  }
}
