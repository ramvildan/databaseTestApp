package com.javatest.databaseTestApp.exception;

public class UserInfoNotFoundException extends BadRequestException {

    public UserInfoNotFoundException(Integer userDetailsId) {
        super(String.format("UserInfo with this Id (%s) does not exist or deleted", userDetailsId));
    }
}
