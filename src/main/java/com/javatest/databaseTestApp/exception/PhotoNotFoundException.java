package com.javatest.databaseTestApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhotoNotFoundException extends BadRequestException{

    public PhotoNotFoundException(String name) {
        super(String.format("Photo with this name (%s) does not exist", name));
    }
}
