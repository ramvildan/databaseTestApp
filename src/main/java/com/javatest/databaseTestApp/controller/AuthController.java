package com.javatest.databaseTestApp.controller;

import com.javatest.databaseTestApp.dto.UserCreateDto;
import com.javatest.databaseTestApp.dto.UserDto;
import com.javatest.databaseTestApp.security.dto.JwtRequest;
import com.javatest.databaseTestApp.security.dto.JwtResponse;
import com.javatest.databaseTestApp.security.dto.RefreshJwtRequest;
import com.javatest.databaseTestApp.service.AuthService;
import com.javatest.databaseTestApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest loginRequest) {

        JwtResponse token = authService.login(loginRequest);

        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {

        JwtResponse token = authService.getAccessToken(request.getRefreshToken());

        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {

        JwtResponse token = authService.getRefreshToken(request.getRefreshToken());

        return ResponseEntity.ok(token);
    }

    @PostMapping("register")
    public UserDto registrationNewUser(@RequestBody
                                       @Valid UserCreateDto userCreateDto) {

        log.info("registrationNewUser: UserCreateDto = {}", userCreateDto);

        return userService.createUser(userCreateDto);
    }
}
