package com.javatest.databaseTestApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhotoNotFoundException extends BadRequestException{

    public PhotoNotFoundException(Integer id) {
        super(String.format("Photo with this id (%s) does not exist", id));
    }
}
