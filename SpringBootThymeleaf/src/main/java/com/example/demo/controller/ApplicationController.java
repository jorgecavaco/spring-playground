package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.services.EmployeeService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController {

  private final String UPLOAD_DIR = "/Users/jca91/workspace/";
  private final EmployeeService employeeService;

  public ApplicationController(EmployeeService employeeService) {
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
      Employee entity = employeeService.getEmployeeById(id);
      model.addAttribute("employee", entity);
    } else {
      model.addAttribute("employee", new Employee());
    }

    return "add-edit-employee";
  }

  @PostMapping(path = "/createEmployee")
  public String createOrUpdateEmployee(@Valid @ModelAttribute Employee employee,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "add-edit-employee";
    }

    if (!employee.getMultipartFileDocument().isEmpty()) {
      try {
        String fileName = StringUtils
            .cleanPath(employee.getMultipartFileDocument().getOriginalFilename());

        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(employee.getMultipartFileDocument().getInputStream(), path,
            StandardCopyOption.REPLACE_EXISTING);

        employee.setImage(fileName);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    employeeService.createOrUpdateEmployee(employee);
    return "redirect:/employees";
  }

  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

    Resource file = loadAsResource(filename);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

  public Resource loadAsResource(String filename) {
    try {
      Path file = Paths.get(UPLOAD_DIR + filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException(
            "Could not read file: " + filename);

      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Could not read file: " + filename, e);
    }
  }
}
