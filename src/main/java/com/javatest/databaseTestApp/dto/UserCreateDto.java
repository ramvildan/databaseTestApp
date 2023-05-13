package com.javatest.databaseTestApp.dto;

import com.javatest.databaseTestApp.entity.type.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDto {

    @Size(min = 1, max = 255)
    @NotBlank
    private String login;

    @Size(min = 1, max = 255)
    @NotBlank
    private String password;

    @NotEmpty
    private Role role;
}
