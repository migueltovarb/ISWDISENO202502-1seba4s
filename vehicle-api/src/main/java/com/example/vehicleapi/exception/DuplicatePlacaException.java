package com.example.vehicleapi.exception;

public class DuplicatePlacaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicatePlacaException(String message) {
        super(message);
    }

    public DuplicatePlacaException(String message, Throwable cause) {
        super(message, cause);
    }
}