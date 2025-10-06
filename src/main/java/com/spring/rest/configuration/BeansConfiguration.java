package com.spring.rest.configuration;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfiguration {

  @Value("${currency.api.basePath}")
  private String currencyApiBasePath;

  @Bean("REST_TEMPLATE")
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder
        .requestFactorySettings(
            ClientHttpRequestFactorySettings.defaults()
                .withConnectTimeout(Duration.ofSeconds(10))
                .withReadTimeout(Duration.ofSeconds(15)))
        .build();
  }

  @Bean("REST_CLIENT")
  public RestClient restClient() {
    return RestClient.builder().baseUrl(currencyApiBasePath).build();
  }
}
