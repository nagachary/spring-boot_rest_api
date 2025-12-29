package com.mylld.amazon.locker;

import java.util.Optional;

import static java.util.Arrays.stream;

public enum Size {
    SMALL("SMALL"),
    MEDIUM("MEDIUM"),
    LARGE("LARGE");

    private final String size;

    private Size(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public static Size of(String inputSize) {
        return stream(values()).filter(size -> size.name().equalsIgnoreCase(inputSize)).findFirst().orElse(null);
    }
}
