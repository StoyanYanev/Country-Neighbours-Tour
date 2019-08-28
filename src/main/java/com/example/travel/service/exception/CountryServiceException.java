package com.example.travel.service.exception;

public class CountryServiceException extends ServiceException {
    public CountryServiceException(final String message) {
        super(message);
    }
    public CountryServiceException(final String message, final Throwable t) {
        super(message, t);
    }
}