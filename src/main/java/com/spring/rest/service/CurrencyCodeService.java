package com.spring.rest.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

public interface CurrencyCodeService {

  <T> ResponseEntity<List> getCurrencyCountries(String currencyCode);
}
