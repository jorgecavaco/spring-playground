package com.example.demo;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {RuntimeException.class, IOException.class})
  public ResponseEntity<String> runtimeException(HttpServletRequest request, RuntimeException ex) {
    return ResponseEntity.status(400).body(ex.getMessage());
  }

  @ExceptionHandler(value = IndexOutOfBoundsException.class)
  public ResponseEntity<String> indexOutOfBoundsException(
      HttpServletRequest request, IndexOutOfBoundsException ex) {
    return ResponseEntity.status(400).body(ex.getMessage());
  }
}
