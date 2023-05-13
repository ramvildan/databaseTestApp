package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.dto.UserDetailsCreateDto;
import com.javatest.databaseTestApp.dto.UserDetailsDto;
import com.javatest.databaseTestApp.dto.UserDetailsUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDetailsService {

    UserDetailsDto createUserDetails(@Valid UserDetailsCreateDto userDetailsCreateDto);

    List<UserDetailsDto> readAllUsersDetails();

    UserDetailsDto updateUserDetails(Integer userId,
                                     @Valid UserDetailsUpdateDto userDetailsUpdateDto);

    void deleteUserDetails(Integer userId);
}
