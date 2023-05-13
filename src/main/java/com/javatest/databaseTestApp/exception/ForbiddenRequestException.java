package com.javatest.databaseTestApp.exception;

public class ForbiddenRequestException extends RuntimeException {

    public ForbiddenRequestException(String message) {
        super(message);
    }
}
