package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.dto.UserCreateDto;
import com.javatest.databaseTestApp.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto create(@Valid UserCreateDto userCreateDto);
}
