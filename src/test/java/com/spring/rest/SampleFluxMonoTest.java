package com.spring.rest;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class SampleFluxMonoTest {

    SampleFluxMono sampleFluxMono = new SampleFluxMono();

    @Test
    void fluxString() {
        var namesFlux = sampleFluxMono.fluxString();
        StepVerifier.create(namesFlux)
                .expectNextCount(3).verifyComplete();
        StepVerifier.create(namesFlux)
                .expectNext("NAME 1", "NAME 2", "NAME 3").verifyComplete();
    }

    @Test
    void monoString() {

        var namesMono = sampleFluxMono.monoString();
        StepVerifier.create(namesMono)
                .expectNext("NAME N").verifyComplete();
    }

    @Test
    void fluxStringFlatMap() {
        var fluxString_flatMap = sampleFluxMono.fluxString_flatMap(5);
        StepVerifier.create(fluxString_flatMap)
                .expectNext("N", "A", "M", "E", "", "3");

    }

    @Test
    void fluxStringFlatMapAsync() {
        var fluxString_flatMap_async = sampleFluxMono.fluxString_flatMap_async(5);
        StepVerifier.create(fluxString_flatMap_async)
                .expectNextCount(6).verifyComplete();

    }

    @Test
    void fluxStringConcatMapAsync() {
        var fluxString_concatMap_async = sampleFluxMono.fluxString_concatMap_async(5);
        StepVerifier.create(fluxString_concatMap_async)
                .expectNextCount(6).verifyComplete();

    }

    @Test
    void fluxStringTransform() {
        var fluxString_transform = sampleFluxMono.fluxString_transform(6);
        StepVerifier.create(fluxString_transform)
                .expectNext("DEFAULT").verifyComplete();
    }

    @Test
    void fluxStringTransformSwitchIfEmpty() {
        var fluxString_transform_switchIfEmpty = sampleFluxMono.fluxString_transform_switchIfEmpty(6);
        StepVerifier.create(fluxString_transform_switchIfEmpty)
                .expectNext("D", "E", "F", "A", "U", "L", "T").verifyComplete();
    }

    @Test
    void fluxStringMerge() {
       var string_merge =  sampleFluxMono.fluxString_merge();
        StepVerifier.create(string_merge).expectNext("Name 1", "Name 3","Name 2", "Name 4").verifyComplete();
    }

    @Test
    void monoStringMergeWith() {
        var string_merge =  sampleFluxMono.monoString_mergeWith();
        StepVerifier.create(string_merge).expectNext("Name M", "Name N").verifyComplete();
    }

    @Test
    void fluxStringMergeSequential() {
      var fluxString_mergeSequential = sampleFluxMono.fluxString_mergeSequential();
      StepVerifier.create(fluxString_mergeSequential).expectNext("Name 1", "Name 2", "Name 3", "Name 4").verifyComplete();
    }

    @Test
    void fluxStringZip() {
        var fluxString_zip = sampleFluxMono.fluxString_zip();
        StepVerifier.create(fluxString_zip).expectNext("Name 1Name 3", "Name 2Name 4").verifyComplete();
    }

    @Test
    void fluxStringZipWith() {
        var fluxString_zipWith = sampleFluxMono.fluxString_zipWith();
        StepVerifier.create(fluxString_zipWith).expectNext("Name 1Name 3", "Name 2Name 4").verifyComplete();
    }

    @Test
    void fluxStringZip1() {
        var fluxString_zip_1 = sampleFluxMono.fluxString_zip_1();
        StepVerifier.create(fluxString_zip_1).expectNext("ac13", "bd24").verifyComplete();
    }

    @Test
    void monoStringZipWith() {
        var monoString_zipWith = sampleFluxMono.monoString_zipWith();
        StepVerifier.create(monoString_zipWith).expectNext("Name 1Name 2").verifyComplete();
    }


}
