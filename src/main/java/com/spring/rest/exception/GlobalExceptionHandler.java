package com.spring.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = HttpClientErrorException.class)
  public ErrorResponse handleClientErrorException(HttpClientErrorException exception) {
    return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.toString());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = NoResourceFoundException.class)
  public ErrorResponse handleNoResourceException(NoResourceFoundException exception) {
    return new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.toString());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = GitHubServiceException.class)
  public ErrorResponse handleGitHubServiceException(GitHubServiceException exception) {
    return exception.getErrorResponse();
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = PinCodeServiceException.class)
  public ErrorResponse handleGitHubServiceException(PinCodeServiceException exception) {
    return exception.getErrorResponse();
  }
}
