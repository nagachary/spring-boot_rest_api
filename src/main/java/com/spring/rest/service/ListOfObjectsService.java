package com.spring.rest.service;

import java.util.List;

public interface ListOfObjectsService {

    <T> T getObjects(List<Integer> id);

}
