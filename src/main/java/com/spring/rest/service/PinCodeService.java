package com.spring.rest.service;

import com.spring.rest.exception.PinCodeServiceException;

public interface PinCodeService {

  <T> T getPinCodeDetails(Integer pinCode) throws PinCodeServiceException;
}
