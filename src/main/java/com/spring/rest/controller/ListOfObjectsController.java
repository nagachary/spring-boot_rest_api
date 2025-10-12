package com.spring.rest.controller;

import com.spring.rest.exception.GitHubServiceException;
import com.spring.rest.service.ListOfObjectsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/objects")
public class ListOfObjectsController {
    Logger logger = LoggerFactory.getLogger(ListOfObjectsController.class);
    @Autowired
    private ListOfObjectsService listOfObjectsService;


    @GetMapping
    public  List<Map<String, Object>> readListOfObjects(@RequestParam List<Integer> ids) throws Exception {
        logger.info("readListOfObjects :");

        if (ids.isEmpty()) {
            throw new Exception("Bad Data Exception :");
        }

        List<Map<String, Object>> result = listOfObjectsService.getObjects(ids);
        logger.info("result : " + result);

        return result;
    }

}
