package com.example.travel.service.exception;

public class ServiceException extends Exception {
    public ServiceException(final String message) {
        super(message);
    }
    public ServiceException(final String message, final Throwable e) {
        super(message, e);
    }
}