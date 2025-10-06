package com.spring.rest.controller;

import com.spring.rest.exception.ErrorResponse;
import com.spring.rest.exception.PinCodeServiceException;
import com.spring.rest.service.PinCodeService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class PinCodeController {
  Logger logger = LoggerFactory.getLogger(PinCodeController.class);

  @Autowired private PinCodeService pinCodeService;

  @GetMapping("/{zipcode}")
  public List readPinCodeData(@PathVariable("zipcode") Integer zipCode)
      throws PinCodeServiceException {
    logger.info("readPinCodeData in PinCodeController");

    if (null == zipCode || 0 == zipCode) {
      throw new PinCodeServiceException(
          new ErrorResponse("Bad Data", HttpStatus.BAD_REQUEST.toString()));
    }

    List output = pinCodeService.getPinCodeDetails(zipCode);
    logger.info("output: {}", output);

    return output;
  }
}
