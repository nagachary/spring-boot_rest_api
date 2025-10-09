package com.spring.rest.service.implementation;

import com.spring.rest.configuration.BeansConfiguration;
import com.spring.rest.service.ListOfObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@Import(BeansConfiguration.class)
public class ListOfObjectsServiceImpl implements ListOfObjectsService {

    private WebClient webClient;

    @Autowired
    public ListOfObjectsServiceImpl(@Qualifier("WEB_CLIENT") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public <T> T getObjects() {

       Flux<List> response = webClient.get().uri("/objects").retrieve().bodyToFlux(List.class);

       return (T) response;
    }
}
