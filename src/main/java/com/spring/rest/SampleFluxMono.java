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

        logger.info("fluxString_transform_switchIfEmpty value is :");
        sampleFlux.fluxString_transform_switchIfEmpty(6).subscribe(x -> logger.info(String.valueOf(x)));

        logger.info("fluxString_concat value is :");
        sampleFlux.fluxString_concat().log().subscribe(logger::info);

        logger.info("fluxString_concatWith value is :");
        sampleFlux.fluxString_concatWith().log().subscribe(logger::info);

        logger.info("monoString_concatWith value is :");
        sampleFlux.monoString_concatWith().log().subscribe(logger::info);

        logger.info("fluxString_merge value is :");
        sampleFlux.fluxString_merge().log().subscribe(logger::info);


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
                .defaultIfEmpty("DEFAULT")
                .log();
    }

    public Flux<String> fluxString_transform_switchIfEmpty(int strLength) {
        Function<Flux<String>, Flux<String>> fluxFunction = input -> input.map(String::toUpperCase)
                .filter(x -> x.length() > strLength)
                .flatMap(this::splitString);

        var defaultFlux = Flux.just("default")
                .transform(fluxFunction);

        return Flux.fromIterable(List.of("Name1", "Name2", "Name 3"))
                .transform(fluxFunction)
                .switchIfEmpty(defaultFlux)
                .log();
    }

    public Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);

    }

    /**
     * flat map doesn't emit the response in the order
     */
    public Flux<String> fluxString_flatMap_async(int strLength) {
        return Flux.fromIterable(List.of("Name1", "Name2", "Name 3"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > strLength)
                .flatMap(this::splitString_withDelay)
                .log();
    }

    /**
     * concat map emits the response in the order
     */
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

    /**
     * concat is a static function in Flux
     * concatWith is an instance method in Flux and Mono
     *
     */
    public Flux<String> fluxString_concat() {
        var firstFlux = Flux.just("Name 1", "Name 2");
        var secondFlux = Flux.just("Name 3", "Name 4");

        return Flux.concat(firstFlux, secondFlux).log();
    }

    public Flux<String> fluxString_concatWith() {
        var firstFlux = Flux.just("Name 1", "Name 2");
        var secondFlux = Flux.just("Name 3", "Name 4");

        return firstFlux.concatWith(secondFlux).log();
    }

    public Flux<String> monoString_concatWith() {
        var firstMono = Mono.just("Name M");
        var secondMono = Flux.just("Name N");

        return firstMono.concatWith(secondMono).log();
    }

    /**
     * merge is a static function in Flux
     * mergeWith is an instance method in Flux and Mono
     *
     */
    public Flux<String> fluxString_merge() {
        var firstFlux = Flux.just("Name 1", "Name 2")
                .delayElements(Duration.ofMillis(100))
                .log();
        var secondFlux = Flux.just("Name 3", "Name 4")
                .delayElements(Duration.ofMillis(110))
                .log();

       // return Flux.merge(firstFlux, secondFlux).log();
        return firstFlux.mergeWith(secondFlux).log();
    }

    public Flux<String> monoString_mergeWith() {
        var firstMono = Mono.just("Name M");
        var secondMono = Flux.just("Name N");

        return firstMono.mergeWith(secondMono).log();
    }
    /**
     * fluxString_mergeSequential is a static function in Flux
     *
     */
    public Flux<String> fluxString_mergeSequential() {
        var firstFlux = Flux.just("Name 1", "Name 2")
                .delayElements(Duration.ofMillis(100))
                .log();
        var secondFlux = Flux.just("Name 3", "Name 4")
                .delayElements(Duration.ofMillis(110))
                .log();

      return Flux.mergeSequential(firstFlux, secondFlux).log();
    }

    /**
     * zip is a static function in Flux
     * zipWith is an instance method in Flux and Mono
     */
    public Flux<String> fluxString_zip() {
        var firstFlux = Flux.just("Name 1", "Name 2");
        var secondFlux = Flux.just("Name 3", "Name 4");

        return Flux.zip(firstFlux, secondFlux, (firstELe, secondEle) -> firstELe + secondEle)
                .log();
    }

    public Flux<String> fluxString_zip_1() {
        var firstFlux = Flux.just("a", "b");
        var secondFlux = Flux.just("c", "d");
        var thirdFlux = Flux.just("1", "2");
        var fourthFlux = Flux.just("3", "4");

        return Flux.zip(firstFlux, secondFlux, thirdFlux, fourthFlux)
                .map(t4 -> t4.getT1() + t4.getT2() + t4.getT3() + t4.getT4())
                .log();
    }

    public Flux<String> fluxString_zipWith() {
        var firstFlux = Flux.just("Name 1", "Name 2");
        var secondFlux = Flux.just("Name 3", "Name 4");

        return firstFlux.zipWith(secondFlux, (firstELe, secondEle) -> firstELe + secondEle)
                .log();
    }

    public Mono<String> monoString_zipWith() {
        var firstFlux = Mono.just("Name 1");
        var secondFlux = Mono.just("Name 2");

        return firstFlux.zipWith(secondFlux)
                .map(t2 -> t2.getT1() + t2.getT2() )
                .log();
    }


}
