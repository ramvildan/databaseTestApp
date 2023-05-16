package com.javatest.databaseTestApp.dto;

import lombok.Builder;

@Builder
public class UserDetailsDto {

    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private String birthday;
    private String phoneNumber;
    private String email;
}
