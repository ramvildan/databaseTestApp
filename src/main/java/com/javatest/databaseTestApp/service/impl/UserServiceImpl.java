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
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final List<UserDto> userDtos;

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {

        User user = User.builder()
                .login(userCreateDto.getLogin())
                .password(passwordEncoder.encode(userCreateDto.getPassword()))
                .role(userCreateDto.getRole())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        userDtos.add(userConverter.fromUserToUserDto(user));

        return userConverter.fromUserToUserDto(
                userRepository.save(user)
        );
    }

    @Override
    public Optional<UserDto> getUserByLogin(String login) {

        return userDtos.stream()
                .filter(userDto -> login.equals(userDto.getLogin()))
                .findFirst();
    }
}
