package com.javatest.databaseTestApp.exception;

public class UserNotFoundException extends BadRequestException {

    public UserNotFoundException(Integer id) {
        super(String.format("User with this Id (%s) does not exist or deleted", id));
    }

    public UserNotFoundException(String login) {
        super(String.format("User with this login (%s) does not exist", login));
    }
}
