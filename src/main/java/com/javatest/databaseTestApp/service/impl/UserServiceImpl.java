package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.converter.UserConverter;
import com.javatest.databaseTestApp.dto.UserCreateDto;
import com.javatest.databaseTestApp.dto.UserDto;
import com.javatest.databaseTestApp.entity.User;
import com.javatest.databaseTestApp.repository.UserRepository;
import com.javatest.databaseTestApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserCreateDto userCreateDto) {

        User user = User.builder()
                .login(userCreateDto.getLogin())
                .password(passwordEncoder.encode(userCreateDto.getPassword()))
                .role(userCreateDto.getRole())
                .createdAt(new Date())
                .updatedAt(new Date())
                .isDeleted(false)
                .build();

        return userConverter.fromUserToUserDto(
                userRepository.save(user)
        );
    }
}
