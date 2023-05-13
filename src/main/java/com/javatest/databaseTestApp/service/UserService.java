package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.dto.UserCreateDto;
import com.javatest.databaseTestApp.dto.UserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    UserDto createUser(@Valid UserCreateDto userCreateDto);

    Optional<UserDto> getByLogin(@NotNull String login);
}
