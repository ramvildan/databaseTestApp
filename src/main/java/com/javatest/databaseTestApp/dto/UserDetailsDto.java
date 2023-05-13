package com.javatest.databaseTestApp.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public class UserDetailsDto {

    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private Date birthday;
    private String phoneNumber;
    private String email;
}
