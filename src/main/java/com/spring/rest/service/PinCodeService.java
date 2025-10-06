package com.spring.rest.service;

import com.spring.rest.exception.PinCodeServiceException;
import java.util.List;

public interface PinCodeService {

  <T> T getPinCodeDetails(Integer pinCode) throws PinCodeServiceException;
}
