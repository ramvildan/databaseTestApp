package com.javatest.databaseTestApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserDetailsUpdateDto {

    @Size(min = 1, max = 255)
    @NotBlank
    private String surname;

    @Size(min = 1, max = 255)
    @NotBlank
    private String name;

    @Size(min = 1, max = 255)
    @NotBlank
    private String patronymic;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotEmpty
    private Date birthday;

    @Size(min = 1, max = 255)
    @NotBlank
    private String phoneNumber;

    @Email(message = "Email wrong format")
    @NotBlank
    private String email;
}
