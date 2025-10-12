package com.spring.rest.service.implementation;

import com.spring.rest.SpringBootRestApplication;
import com.spring.rest.configuration.BeansConfiguration;
import com.spring.rest.service.ListOfObjectsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Import(BeansConfiguration.class)
public class ListOfObjectsServiceImpl implements ListOfObjectsService {
    private static final Logger logger = LoggerFactory.getLogger(ListOfObjectsServiceImpl.class);
    private WebClient webClient;

    @Autowired
    public ListOfObjectsServiceImpl(@Qualifier("WEB_CLIENT") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public <T> T getObjects(List<Integer> id) {
        logger.info("getObjects : ");
       Mono<List<Map>> listMono = webClient
               .get()
               .uri("/objects")
               .retrieve()
               .bodyToFlux(Map.class)
               .collectList();
       List<Map<String, Object>> resultMapList = new ArrayList<>();

        logger.info("resultMapList : "+resultMapList);
       return (T) resultMapList;
    }
}
