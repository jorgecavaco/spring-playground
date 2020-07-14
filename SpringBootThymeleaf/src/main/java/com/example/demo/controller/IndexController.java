package com.example.demo.controller;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.services.EmployeeService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

  private final EmployeeService employeeService;

  public IndexController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/")
  public String homePage() {
    return "index";
  }

  @GetMapping("/employees")
  public String employees(Model model) {

    model.addAttribute("employees", employeeService.getEmployees());
    return "employees";
  }

  @GetMapping("/delete/{id}")
  public String deleteEmployeeById(Model model, @PathVariable("id") Long id) {
    employeeService.delete(id);

    return "redirect:/employees";
  }

  @GetMapping(path = {"/edit", "/edit/{id}"})
  public String editEmployeeById(Model model,
      @PathVariable(value = "id", required = false) Long id) {
    if (id != null) {
      EmployeeEntity entity = employeeService.getEmployeeById(id);
      model.addAttribute("employee", entity);
    } else {
      model.addAttribute("employee", new EmployeeEntity());
    }
    return "add-edit-employee";
  }

  @PostMapping(path = "/createEmployee")
  public String createOrUpdateEmployee(@Valid EmployeeEntity employee, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "add-edit-employee";
    }

    employeeService.createOrUpdateEmployee(employee);
    return "redirect:/employees";
  }
}
