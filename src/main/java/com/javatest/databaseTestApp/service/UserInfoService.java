package com.javatest.databaseTestApp.service;

import com.javatest.databaseTestApp.dto.UserInfoCreateDto;
import com.javatest.databaseTestApp.dto.UserInfoDto;
import com.javatest.databaseTestApp.dto.UserInfoUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInfoService {

    UserInfoDto create(@Valid UserInfoCreateDto userInfoCreateDto);

    List<UserInfoDto> readAll();

    UserInfoDto getById(Integer userInfoId);

    UserInfoDto update(Integer userInfoId,
                       @Valid UserInfoUpdateDto userInfoUpdateDto);

    void delete(Integer userInfoId);
}
