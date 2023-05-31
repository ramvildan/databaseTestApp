package com.javatest.databaseTestApp.exception;

public class PhotoNotFoundException extends BadRequestException {

    public PhotoNotFoundException(Integer id) {
        super(String.format("Photo with this id (%s) does not exist", id));
    }
}
