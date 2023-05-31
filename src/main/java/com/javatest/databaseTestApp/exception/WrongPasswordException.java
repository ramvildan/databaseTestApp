package com.javatest.databaseTestApp.exception;

public class WrongPasswordException extends ForbiddenRequestException {

    public WrongPasswordException(String message) {
        super(message);
    }
}
