package com.javatest.databaseTestApp.exception.handler;

import com.javatest.databaseTestApp.exception.BadRequestException;
import com.javatest.databaseTestApp.exception.ForbiddenRequestException;
import com.javatest.databaseTestApp.exception.PhotoNotFoundException;
import com.javatest.databaseTestApp.exception.UserInfoNotFoundException;
import com.javatest.databaseTestApp.exception.UserNotFoundException;
import com.javatest.databaseTestApp.exception.WrongPasswordException;
import com.javatest.databaseTestApp.exception.WrongTokenException;
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
    public ResponseError handle(BadRequestException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseError handle(ForbiddenRequestException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.FORBIDDEN, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(UserNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(PhotoNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(UserInfoNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseError handle(WrongPasswordException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.FORBIDDEN, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseError handle(WrongTokenException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(HttpStatus.FORBIDDEN, exception.getMessage());
    }
}
