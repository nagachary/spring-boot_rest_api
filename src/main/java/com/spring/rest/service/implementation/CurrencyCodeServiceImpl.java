package com.spring.rest.service.implementation;

import com.spring.rest.configuration.BeansConfiguration;
import com.spring.rest.service.CurrencyCodeService;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Import(BeansConfiguration.class)
public class CurrencyCodeServiceImpl implements CurrencyCodeService {
  Logger logger = LoggerFactory.getLogger(CurrencyCodeServiceImpl.class);

  private final RestClient restClient;

  @Autowired
  public CurrencyCodeServiceImpl(@Qualifier("REST_CLIENT") RestClient restClient) {
    this.restClient = restClient;
  }

  @Override
  public <T> ResponseEntity<List> getCurrencyCountries(String currencyCode) {
    logger.info("getCurrencyCountries :");
    ResponseEntity<List> responseEntity =
        restClient
            .get()
            .uri(
                urlBuilder -> URI.create(urlBuilder.path("/currency/" + currencyCode).toUriString()))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(List.class);

    return responseEntity;
  }
}
