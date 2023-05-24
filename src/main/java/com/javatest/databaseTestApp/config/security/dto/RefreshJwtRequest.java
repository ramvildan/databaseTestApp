package com.javatest.databaseTestApp.config.security.dto;

import lombok.Data;

@Data
public class RefreshJwtRequest {

    public String refreshToken;
}
