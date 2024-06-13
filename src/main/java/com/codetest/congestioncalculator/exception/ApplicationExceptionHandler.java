package com.codetest.congestioncalculator.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(GeneralException.class)
  public ResponseEntity<Map<String, String>> handleGeneralException(GeneralException exception) {
    return new ResponseEntity<>(errorResponse(exception), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IOException.class)
  public ResponseEntity<Map<String, String>> handleIOException(IOException exception) {
    return new ResponseEntity<>(
        errorResponse(new GeneralException(exception.getMessage())), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(JsonMappingException.class)
  public ResponseEntity<Map<String, String>> handleJsonException(JsonMappingException exception) {
    return new ResponseEntity<>(
        errorResponse(new GeneralException("Unknown Vehicle Type or malformed request body")),
        HttpStatus.BAD_REQUEST);
  }

  private Map<String, String> errorResponse(GeneralException exception) {
    Map<String, String> response = new HashMap<>();
    response.put("error", exception.getError());
    response.put("message", exception.getMessage());
    return response;
  }
}
