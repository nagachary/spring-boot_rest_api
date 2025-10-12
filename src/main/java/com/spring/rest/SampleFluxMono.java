package com.spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class SampleFluxMono {

    private static final Logger logger = LoggerFactory.getLogger(SampleFluxMono.class);
    public static void main(String[] args) {

        SampleFluxMono sampleFlux = new SampleFluxMono();
        logger.info("Flux values are :");
        sampleFlux.fluxString().subscribe(logger::info);
        logger.info("Mono value is :");
        sampleFlux.monoString().subscribe(logger::info);
    }

    private Flux<String> fluxString() {
        return Flux.fromIterable(List.of("Name 1", "Name 2", "Name 3")).log();
    }

    private Mono<String> monoString() {
        return Mono.just("Name N").log();
    }
}
