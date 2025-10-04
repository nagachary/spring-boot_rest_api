package com.spring.rest.service.implementation;

import com.spring.rest.exception.ErrorResponse;
import com.spring.rest.exception.PinCodeServiceException;
import com.spring.rest.service.PinCodeService;
import java.net.URI;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public final class PinCodeServiceImpl implements PinCodeService {
  Logger logger = LoggerFactory.getLogger(PinCodeServiceImpl.class);

  @Qualifier("REST_TEMPLATE")
  private final RestTemplate restTemplate;

  public PinCodeServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Object getPinCodeDetails(Integer zipcode) throws PinCodeServiceException {
    logger.info("getPinCodeDetails in PinCodeServiceImpl");

    HashMap<Object, Object> map = new HashMap<>();
    map.put("Accept", MediaType.APPLICATION_JSON_VALUE);

    String url = "https://api.postalpincode.in/pincode/" + zipcode;
    ResponseEntity<Object> response = null;
    try {

      response =
          restTemplate.exchange(new URI(url), HttpMethod.GET, new HttpEntity<>(map), Object.class);

    } catch (Exception exp) {
      throw new PinCodeServiceException(
          new ErrorResponse(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()));
    }

    return response;
  }
}
