package com.pedrolima.eventmanager.exceptions;

public class GenericErrorResponse {

  private String message;

  public GenericErrorResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
