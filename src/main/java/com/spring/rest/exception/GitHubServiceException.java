package com.spring.rest.exception;

public class GitHubServiceException extends Exception {

  private final ErrorResponse errorResponse;

  public GitHubServiceException(ErrorResponse errorResponse) {
    super(errorResponse.errorMessage());
    this.errorResponse = errorResponse;
  }

  public String getErrorMessage() {
    return errorResponse.errorMessage();
  }

  public String getErrorCode() {
    return errorResponse.errorCode();
  }

  public ErrorResponse getErrorResponse() {
    return errorResponse;
  }
}
