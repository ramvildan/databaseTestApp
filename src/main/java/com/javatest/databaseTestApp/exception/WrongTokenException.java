package com.javatest.databaseTestApp.exception;

public class WrongTokenException extends ForbiddenRequestException {

    public WrongTokenException(String message) {
        super(message);
    }
}
