package com.spring.rest.controller;

import com.spring.rest.service.CurrencyCodeService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CurrencyCodeController {
  Logger logger = LoggerFactory.getLogger(CurrencyCodeController.class);

  @Autowired private CurrencyCodeService currencyCodeService;

  @GetMapping("/currency/{currencyCode}")
  public List getCurrencyCodeDetails(@PathVariable("currencyCode") String currencyCode) {
    logger.info("getCurrencyCodeDetails : ");
    ResponseEntity<List> currencyCodes = currencyCodeService.getCurrencyCountries(currencyCode);
    List response = null;

    if (currencyCodes != null && currencyCodes.getStatusCode().is2xxSuccessful()) {
      response = currencyCodes.getBody();
    } else {
      logger.info("error code :");
    }

    return response;
  }
}
