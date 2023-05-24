package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.entity.User;
import com.javatest.databaseTestApp.exception.UserNotFoundException;
import com.javatest.databaseTestApp.exception.WrongPasswordException;
import com.javatest.databaseTestApp.exception.WrongTokenException;
import com.javatest.databaseTestApp.repository.UserRepository;
import com.javatest.databaseTestApp.config.security.provider.JwtProvider;
import com.javatest.databaseTestApp.config.security.dto.JwtRequest;
import com.javatest.databaseTestApp.config.security.dto.JwtResponse;
import com.javatest.databaseTestApp.service.AuthService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final Map<String, String> refreshStorage = new HashMap<>();

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {

        User user = userRepository
                .findByLoginAndIsDeletedIsFalse(loginRequest.getLogin())
                .orElseThrow(() -> new UserNotFoundException(loginRequest.getLogin()));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);

            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new WrongPasswordException("Wrong password");
        }
    }

    @Override
    public JwtResponse getAccessToken(String refreshToken) {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                User user = userRepository
                        .findByLoginAndIsDeletedIsFalse(login)
                        .orElseThrow(() -> new UserNotFoundException(login));

                String accessToken = jwtProvider.generateAccessToken(user);

                return new JwtResponse(accessToken, null);
            }
        }

        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse getRefreshToken(String refreshToken) {

        if (jwtProvider.validateRefreshToken(refreshToken)) {

            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                User user = userRepository
                        .findByLoginAndIsDeletedIsFalse(login)
                        .orElseThrow(() -> new UserNotFoundException(login));

                String accessToken = jwtProvider.generateAccessToken(user);
                String newRefreshToken = jwtProvider.generateRefreshToken(user);

                return new JwtResponse(null, newRefreshToken);
            }
        }

        throw new WrongTokenException("Invalid JWT token");
    }
}
