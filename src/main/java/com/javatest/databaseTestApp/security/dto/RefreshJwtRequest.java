package com.javatest.databaseTestApp.security.dto;

import lombok.Data;

@Data
public class RefreshJwtRequest {

    public String refreshToken;
}
