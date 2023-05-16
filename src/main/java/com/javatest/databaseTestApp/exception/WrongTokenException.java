package com.javatest.databaseTestApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class WrongTokenException extends ForbiddenRequestException {

    public WrongTokenException(String message) {
        super(message);
    }
}
