package com.portfolio.template.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DataProcessingException(String message) {
        super(message);
    }
}
