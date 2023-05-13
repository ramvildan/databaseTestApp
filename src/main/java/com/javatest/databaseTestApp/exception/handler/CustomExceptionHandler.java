package com.javatest.databaseTestApp.exception.handler;

import com.javatest.databaseTestApp.exception.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(UserNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}