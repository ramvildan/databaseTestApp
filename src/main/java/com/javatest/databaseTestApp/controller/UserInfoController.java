package com.javatest.databaseTestApp.controller;

import com.javatest.databaseTestApp.dto.UserInfoCreateDto;
import com.javatest.databaseTestApp.dto.UserInfoDto;
import com.javatest.databaseTestApp.dto.UserInfoUpdateDto;
import com.javatest.databaseTestApp.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users/info")
@RequiredArgsConstructor
@Log4j2
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PostMapping
    public ResponseEntity<UserInfoDto> create(@RequestBody UserInfoCreateDto userInfoCreateDto) {

        log.info("createInfo: userInfoCreateDto = {}", userInfoCreateDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userInfoService.create(userInfoCreateDto));
    }

    @GetMapping
    public ResponseEntity<List<UserInfoDto>> readAll() {

        log.info("readAllUserInfo: ");

        return ResponseEntity.status(HttpStatus.OK)
                .body(userInfoService.readAll());
    }

    @GetMapping("/{userInfoId}")
    public ResponseEntity<UserInfoDto> get(@PathVariable Integer userInfoId) {

        log.info("getUserInfo: userInfoId = {}", userInfoId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userInfoService.getById(userInfoId));
    }

    @PutMapping("/{userInfoId}")
    public ResponseEntity<UserInfoDto> update(@RequestBody UserInfoUpdateDto userInfoUpdateDto,
                                              @PathVariable Integer userInfoId) {

        log.info("updateInfo: userInfoUpdateDto = {}, userId = {}", userInfoUpdateDto, userInfoId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userInfoService.update(userInfoId, userInfoUpdateDto));
    }

    @DeleteMapping("/{userInfoId}")
    public ResponseEntity<Void> delete(@PathVariable Integer userInfoId) {

        log.info("deleteInfo: userId = {}", userInfoId);

        userInfoService.delete(userInfoId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
