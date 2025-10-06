package com.spring.rest.exception;

public class PinCodeServiceException extends Exception {
  private final ErrorResponse errorResponse;

  public PinCodeServiceException(ErrorResponse errorResponse) {
    super(errorResponse.errorMessage());
    this.errorResponse = errorResponse;
  }

  public ErrorResponse getErrorResponse() {
    return errorResponse;
  }

  public String getErrorMessage() {
    return errorResponse.errorMessage();
  }

  public String getErrorCode() {
    return errorResponse.errorCode();
  }
}
