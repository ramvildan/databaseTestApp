package com.javatest.databaseTestApp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserInfoDto {

    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthday;
    private String phoneNumber;
    private String email;
}
