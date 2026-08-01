package com.example.apisample.utils;

public final class Constants {

    // Prevents accidental instantiation.
    // Single source for constants.
    // Easy to expand later.
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
}
