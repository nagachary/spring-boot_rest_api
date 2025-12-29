package com.spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mylld.*", "com.spring.*"})
public class SpringBootRestApplication {
  private static final Logger logger = LoggerFactory.getLogger(SpringBootRestApplication.class);

  public static void main(String[] args) {
    logger.info("Main in com.spring.rest.SpringBootRestApplication");
    SpringApplication.run(SpringBootRestApplication.class, args);
  }
}
