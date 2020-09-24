package com.lmsilva.geocoding.exception;

public class MissingRequiredParameterException extends Exception {
    public MissingRequiredParameterException(String message) {
        super(message);
    }

    MissingRequiredParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
