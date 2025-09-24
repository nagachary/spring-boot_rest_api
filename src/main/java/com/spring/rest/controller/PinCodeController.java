package com.spring.rest.controller;

import com.spring.rest.service.PinCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class PinCodeController {

    @Autowired
    private PinCodeService pinCodeService;

    @GetMapping("/{pinCode}")
    public String readPinCodeData(@PathVariable Integer pinCode) {

       String output =  (String) pinCodeService.getPinCodeDetails(pinCode);
        System.out.println("pinCode :"+pinCode);

        return output;
    }

}
