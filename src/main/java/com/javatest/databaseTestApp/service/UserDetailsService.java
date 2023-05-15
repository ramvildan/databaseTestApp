package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.dto.UserDetailsCreateDto;
import com.javatest.databaseTestApp.dto.UserDetailsDto;
import com.javatest.databaseTestApp.dto.UserDetailsUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserDetailsService {

    UserDetailsDto create(@Valid UserDetailsCreateDto userDetailsCreateDto);

    List<UserDetailsDto> readAll();

    UserDetailsDto update(Integer userId,
                          @Valid UserDetailsUpdateDto userDetailsUpdateDto);

    void delete(Integer userId);
}
