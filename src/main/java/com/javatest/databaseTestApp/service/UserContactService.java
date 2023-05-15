package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.dto.UserContactCreateDto;
import com.javatest.databaseTestApp.dto.UserContactDto;
import com.javatest.databaseTestApp.dto.UserContactUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserContactService {

    UserContactDto create(Integer userId,
                          @Valid UserContactCreateDto userContactCreateDto);

    List<UserContactDto> readAll();

    UserContactDto update(Integer userId,
                          @Valid UserContactUpdateDto userContactUpdateDto);

    void delete(Integer userId);
}
