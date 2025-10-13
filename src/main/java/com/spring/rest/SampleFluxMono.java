package com.spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class SampleFluxMono {

    private static final Logger logger = LoggerFactory.getLogger(SampleFluxMono.class);
    public static void main(String[] args) {

        SampleFluxMono sampleFlux = new SampleFluxMono();
        logger.info("Flux values are :");
        sampleFlux.fluxString().subscribe(logger::info);
        logger.info("Mono value is :");
        sampleFlux.monoString().subscribe(logger::info);
        logger.info("fluxString_flatMap values are :");
        sampleFlux.fluxString_flatMap(5).subscribe(logger::info);

        logger.info("fluxString_flatMap_async values are :");
        sampleFlux.fluxString_flatMap_async(5).subscribe(logger::info);

        logger.info("fluxString_concatMap_async values are :");
        sampleFlux.fluxString_concatMap_async(5).subscribe(logger::info);

        logger.info("monoString_flatMap value is :");
        sampleFlux.monoString_flatMap().subscribe(x -> logger.info(String.valueOf(x)));

        logger.info("monoString_flatMapMany value is :");
        sampleFlux.monoString_flatMapMany().subscribe(x -> logger.info(String.valueOf(x)));

        logger.info("fluxString_transform value is :");
        sampleFlux.fluxString_transform(5).subscribe(x -> logger.info(String.valueOf(x)));



    }

    public Flux<String> fluxString() {
        return Flux.fromIterable(List.of("Name 1", "Name 2", "Name 3"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fluxString_flatMap(int strLength) {
        return Flux.fromIterable(List.of("Name1", "Name2", "Name 3"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > strLength)
                .flatMap(this::splitString)
                .log();
    }

    public Flux<String> fluxString_transform(int strLength) {
        Function<Flux<String>, Flux<String>> fluxFunction = input -> input.map(String::toUpperCase)
                .filter(x -> x.length() > strLength);
        return Flux.fromIterable(List.of("Name1", "Name2", "Name 3"))
                .transform(fluxFunction)
                .flatMap(this::splitString)
                .log();
    }



    public Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);

    }

    public Flux<String> fluxString_flatMap_async(int strLength) {
        return Flux.fromIterable(List.of("Name1", "Name2", "Name 3"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > strLength)
                .flatMap(this::splitString_withDelay)
                .log();
    }

    public Flux<String> fluxString_concatMap_async(int strLength) {
        return Flux.fromIterable(List.of("Name1", "Name2", "Name 3"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > strLength)
                .concatMap(this::splitString_withDelay)
                .log();
    }


    public Flux<String> splitString_withDelay(String name) {
        var charArray = name.split("");
        var delay = new Random().nextInt(1000);

        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(delay))
                .log();

    }

    public Mono<String> monoString() {
        return Mono.just("Name N")
                .map(String::toUpperCase)
                .log();
    }


    public Mono<List<String>> monoString_flatMap() {
        return Mono.just("Name N")
                .map(String::toUpperCase)
                .flatMap(this::splitStringMono)
                .log();
    }

    public Mono<List<String>> splitStringMono(String s) {
        var charArray = s.split("");
        var charList = List.of(charArray);
       return Mono.just(charList);

    }

    public Flux<String> monoString_flatMapMany() {
        return Mono.just("Name N")
                .map(String::toUpperCase)
                .flatMapMany(this::splitString)
                .log();
    }
}
