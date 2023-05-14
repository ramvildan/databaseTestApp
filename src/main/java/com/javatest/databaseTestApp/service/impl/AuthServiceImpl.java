package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.converter.UserConverter;
import com.javatest.databaseTestApp.entity.User;
import com.javatest.databaseTestApp.exception.UserNotFoundException;
import com.javatest.databaseTestApp.exception.WrongPasswordException;
import com.javatest.databaseTestApp.exception.WrongTokenException;
import com.javatest.databaseTestApp.security.dto.JwtProvider;
import com.javatest.databaseTestApp.security.dto.JwtRequest;
import com.javatest.databaseTestApp.security.dto.JwtResponse;
import com.javatest.databaseTestApp.service.AuthService;
import com.javatest.databaseTestApp.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final UserConverter userConverter;

    private final Map<String, String> refreshStorage = new HashMap<>();

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {

        User user = userConverter.fromUserDtoToUser(
                userService.getUserByLogin(loginRequest.getLogin())
                .orElseThrow(() -> new UserNotFoundException(loginRequest.getLogin())));

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

            if (!isNull(saveRefreshToken) && saveRefreshToken.equals(refreshToken)) {

                User user = userConverter.fromUserDtoToUser(
                        userService.getUserByLogin(login)
                        .orElseThrow(() -> new UserNotFoundException(login)));

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

            if (!isNull(saveRefreshToken) && saveRefreshToken.equals(refreshToken)) {

                User user = userConverter.fromUserDtoToUser(
                        userService.getUserByLogin(login)
                                .orElseThrow(() -> new UserNotFoundException(login)));

                String newRefreshToken = jwtProvider.generateRefreshToken(user);

                return new JwtResponse(null, newRefreshToken);
            }
        }

        throw new WrongTokenException("Invalid JWT token");
    }
}
