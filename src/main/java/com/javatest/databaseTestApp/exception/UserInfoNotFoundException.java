package com.javatest.databaseTestApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserInfoNotFoundException extends BadRequestException{

    public UserInfoNotFoundException(Integer userDetailsId) {
        super(String.format("UserInfo with this Id (%s) does not exist or deleted", userDetailsId));
    }
}
