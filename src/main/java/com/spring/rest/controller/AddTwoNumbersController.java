package com.spring.rest.controller;

import com.spring.rest.model.AddTwoNumbersRequest;
import com.spring.rest.model.AdditionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/add")
public class AddTwoNumbersController {
    private static final Logger logger = LoggerFactory.getLogger(AddTwoNumbersController.class);

    @PostMapping
    public @ResponseBody ResponseEntity<?> addTwoNumbers(@RequestBody AddTwoNumbersRequest request) {
        logger.info("addTwoNumbers : ");
        if(null == request || null == request.getNumber_1() || null == request.getNumber_2()) {
            logger.info("invalid input:");
            return new ResponseEntity<String>(String.valueOf(Map.of("message", "number_1 and number_2 are required")), HttpStatusCode.valueOf(400));
        }
        int result = request.getNumber_1() + request.getNumber_2();
        logger.info("result :"+result);
        return new ResponseEntity<AdditionResult>(new AdditionResult(result), HttpStatusCode.valueOf(201));
    }

}
