package com.codetest.congestioncalculator.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralException extends Exception {

  public static final String GENERAL_ERROR_HEADING = "General Error";
  String error;
  String message;

  public GeneralException(String error, String message, Throwable cause) {
    super(message, cause);
    this.error = error;
    this.message = message;
  }

  public GeneralException(String error, Throwable cause) {
    this(error, "Unknown Error", cause);
  }

  public GeneralException(String message) {
    this(GENERAL_ERROR_HEADING, message, null);
  }
}
