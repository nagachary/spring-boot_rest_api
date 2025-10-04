package com.spring.rest.controller;

import com.spring.rest.exception.ErrorResponse;
import com.spring.rest.exception.PinCodeServiceException;
import com.spring.rest.service.PinCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class PinCodeController {
  Logger logger = LoggerFactory.getLogger(PinCodeController.class);

  @Autowired private PinCodeService pinCodeService;

  @GetMapping("/{zipcode}")
  public ResponseEntity<Object> readPinCodeData(@PathVariable("zipcode") Integer zipCode)
      throws PinCodeServiceException {
    logger.info("readPinCodeData in PinCodeController");

    if (null == zipCode || 0 == zipCode) {
      throw new PinCodeServiceException(
          new ErrorResponse("Bad Data", HttpStatus.BAD_REQUEST.toString()));
    }

    ResponseEntity<Object> output = pinCodeService.getPinCodeDetails(zipCode);
    logger.info("output: {}", output);

    return output;
  }
}
