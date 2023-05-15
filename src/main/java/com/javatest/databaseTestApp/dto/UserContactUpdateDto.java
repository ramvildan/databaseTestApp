package com.javatest.databaseTestApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserContactUpdateDto {

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
    @NotBlank
    private String phoneNumber;
}
