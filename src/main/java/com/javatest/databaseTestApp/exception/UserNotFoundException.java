package com.javatest.databaseTestApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends BadRequestException{

    public UserNotFoundException(Integer id) {
        super(String.format("UserDetails with this Id (%s) does not exist", id));
    }

    public UserNotFoundException(String login) {
        super(String.format("UserDetails with this login (%s) does not exist", login));
    }
}