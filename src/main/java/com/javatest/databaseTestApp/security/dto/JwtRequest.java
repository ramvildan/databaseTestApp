package com.javatest.databaseTestApp.security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {

    @NotNull
    private String login;

    @NotNull
    private String password;
}
