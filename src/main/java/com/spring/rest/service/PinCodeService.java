package com.spring.rest.service;

public sealed interface PinCodeService permits PinCodeServiceImpl {

    Object getPinCodeDetails(Integer pinCode);
}
