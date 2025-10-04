package com.spring.rest.service;

import com.spring.rest.exception.PinCodeServiceException;

public interface PinCodeService {

  Object getPinCodeDetails(Integer pinCode) throws PinCodeServiceException;
}
