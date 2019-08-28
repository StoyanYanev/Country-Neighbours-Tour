package com.example.travel.service.exception;

public class CurrencyServiceException extends ServiceException {
    public CurrencyServiceException(final String message) {
        super(message);
    }
    public CurrencyServiceException(final String message, final Throwable e) {
        super(message, e);
    }

}