package com.spring.rest;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class SampleFluxMonoTest {

    SampleFluxMono sampleFluxMono = new SampleFluxMono();

    @Test
    void fluxString() {
        var namesFlux = sampleFluxMono.fluxString();
        StepVerifier.create(namesFlux).expectNextCount(3).verifyComplete();
        StepVerifier.create(namesFlux).expectNext("NAME 1", "NAME 2", "NAME 3").verifyComplete();
    }

    @Test
    void monoString() {

        var namesMono = sampleFluxMono.monoString();
        StepVerifier.create(namesMono).expectNext("Name N").verifyComplete();
    }

    @Test
    void fluxStringFlatMap() {
        var fluxString_flatMap = sampleFluxMono.fluxString_flatMap(5);
        StepVerifier.create(fluxString_flatMap).expectNext("N", "A", "M", "E", "", "3");

    }

    @Test
    void fluxStringFlatMapAsync() {
        var fluxString_flatMap_async = sampleFluxMono.fluxString_flatMap_async(5);
        StepVerifier.create(fluxString_flatMap_async).expectNextCount(6).verifyComplete();

    }

    @Test
    void fluxStringConcatMapAsync() {
        var fluxString_concatMap_async = sampleFluxMono.fluxString_concatMap_async(5);
        StepVerifier.create(fluxString_concatMap_async).expectNext("N", "A", "M", "E", "", "3").verifyComplete();

    }
}
