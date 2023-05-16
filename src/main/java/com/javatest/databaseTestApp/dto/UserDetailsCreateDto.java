package com.javatest.databaseTestApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserDetailsCreateDto {

    @Size(min = 1, max = 255)
    @NotBlank
    private String surname;

    @Size(min = 1, max = 255)
    @NotBlank
    private String name;

    @Size(min = 1, max = 255)
    @NotBlank
    private String patronymic;

    @Size(min = 1, max = 255)
    private String birthday;

    @Size(min = 1, max = 255)
    @NotBlank
    private String phoneNumber;

    @Size(min = 1, max = 255)
    private String email;
}
