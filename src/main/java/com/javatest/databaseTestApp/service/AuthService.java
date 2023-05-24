package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.config.security.dto.JwtRequest;
import com.javatest.databaseTestApp.config.security.dto.JwtResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    JwtResponse login(@NotNull JwtRequest loginRequest);

    JwtResponse getAccessToken(@NotNull String refreshToken);

    JwtResponse getRefreshToken(@NotNull String refreshToken);
}
