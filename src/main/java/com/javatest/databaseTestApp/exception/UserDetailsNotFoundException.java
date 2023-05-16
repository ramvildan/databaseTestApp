package com.javatest.databaseTestApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserDetailsNotFoundException extends BadRequestException{

    public UserDetailsNotFoundException(Integer userDetailsId) {
        super(String.format("UserDetails with this Id (%s) does not exist or deleted", userDetailsId));
    }
}
