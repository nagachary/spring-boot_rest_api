package com.spring.rest.service.implementation;

import com.spring.rest.exception.ErrorResponse;
import com.spring.rest.exception.PinCodeServiceException;
import com.spring.rest.service.PinCodeService;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public final class PinCodeServiceImpl implements PinCodeService {
  Logger logger = LoggerFactory.getLogger(PinCodeServiceImpl.class);

  private final RestTemplate restTemplate;

  @Autowired
  public PinCodeServiceImpl(@Qualifier("REST_TEMPLATE") RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List getPinCodeDetails(Integer zipcode) throws PinCodeServiceException {
    logger.info("getPinCodeDetails in PinCodeServiceImpl");

    HashMap<Object, Object> map = new HashMap<>();
    map.put("Accept", MediaType.APPLICATION_JSON_VALUE);

    String url = "http://api.postalpincode.in/pincode/" + zipcode;
    List response = null;
    try {

      response =
          restTemplate
              .exchange(new URI(url), HttpMethod.GET, new HttpEntity<>(map), List.class)
              .getBody();

    } catch (Exception exp) {
      throw new PinCodeServiceException(
          new ErrorResponse(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()));
    }

    return response;
  }
}
