package com.example.travel.service;

public enum RequestCountryType {
    REQUEST_TYPE_NAME("https://restcountries-v1.p.rapidapi.com/name/"),
    REQUEST_TYPE_CODE("https://restcountries-v1.p.rapidapi.com/alpha/");

    private final String value;

    RequestCountryType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}