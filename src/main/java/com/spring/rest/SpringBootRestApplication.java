package com.spring.rest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class SpringBootRestApplication {

    public static void main(String[] args) {
        System.out.println("Main in com.spring.rest.SpringBootRestApplication");
        SpringApplication.run(SpringBootRestApplication.class, args);
    }
}
