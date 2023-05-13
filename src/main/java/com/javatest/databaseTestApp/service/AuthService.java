package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.security.dto.JwtRequest;
import com.javatest.databaseTestApp.security.dto.JwtResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

//  нужен ли здесь NotNull, если он есть в JwtRequest, может быть здесь сделать Valid?
    JwtResponse login(@NotNull JwtRequest loginRequest);

    JwtResponse getAccessToken(@NotNull String refreshToken);

    JwtResponse getRefreshToken(@NotNull String refreshToken);
}
