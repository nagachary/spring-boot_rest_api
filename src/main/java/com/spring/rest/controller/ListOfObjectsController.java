package com.spring.rest.controller;

import com.spring.rest.exception.GitHubServiceException;
import com.spring.rest.service.ListOfObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objects")
public class ListOfObjectsController {

    private ListOfObjectsService listOfObjectsService;

    public ListOfObjectsController(@Autowired ListOfObjectsService listOfObjectsService) {
        this.listOfObjectsService = listOfObjectsService;
    }

    @GetMapping
    public List readListOfObjects(@RequestParam List<Integer> id) throws Exception {

        if(id.isEmpty()) {
            throw new Exception("Bad Data Exception :");
        }

        return List.of();
    }

}
