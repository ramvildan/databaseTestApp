package com.javatest.databaseTestApp.dto;

import lombok.Builder;

@Builder
public class UserContactDto {

    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
}
