package com.example.demo.model;

import com.example.demo.entity.EmployeeEntity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class Employee {

  private Long id;

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @Email
  private String email;

  private String image;

  private MultipartFile multipartFileDocument;

  public static Employee from(EmployeeEntity employeeEntity) {
    Employee employee = new Employee();
    employee.setId(employeeEntity.getId());
    employee.setFirstName(employeeEntity.getFirstName());
    employee.setLastName(employeeEntity.getLastName());
    employee.setEmail(employeeEntity.getEmail());
    employee.setImage(employeeEntity.getImage());

    return employee;
  }

  public EmployeeEntity copy(EmployeeEntity employeeEntity) {
    employeeEntity.setFirstName(firstName);
    employeeEntity.setLastName(lastName);
    employeeEntity.setEmail(email);
    employeeEntity.setImage(image);

    return employeeEntity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public MultipartFile getMultipartFileDocument() {
    return multipartFileDocument;
  }

  public void setMultipartFileDocument(
      MultipartFile multipartFileDocument) {
    this.multipartFileDocument = multipartFileDocument;
  }
}
