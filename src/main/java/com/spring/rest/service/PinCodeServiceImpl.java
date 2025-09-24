package com.spring.rest.service;

import org.springframework.stereotype.Service;


@Service
public final class PinCodeServiceImpl implements PinCodeService {

    @Override
    public Object getPinCodeDetails(Integer pinCode) {

        return "pinCode : "+pinCode;
    }
}
