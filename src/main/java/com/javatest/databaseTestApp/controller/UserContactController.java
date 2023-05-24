package com.javatest.databaseTestApp.controller;

import com.javatest.databaseTestApp.dto.UserContactCreateDto;
import com.javatest.databaseTestApp.dto.UserContactDto;
import com.javatest.databaseTestApp.dto.UserContactUpdateDto;
import com.javatest.databaseTestApp.dto.UserInfoDto;
import com.javatest.databaseTestApp.service.UserContactService;
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
@RequestMapping("users/contacts")
@RequiredArgsConstructor
@Log4j2
public class UserContactController {

    private final UserContactService userContactService;

    @PostMapping
    public ResponseEntity<UserContactDto> create(@RequestBody UserContactCreateDto userContactCreateDto) {

        log.info("createContact: userContactCreateDto = {}", userContactCreateDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userContactService.create(userContactCreateDto));
    }

    @GetMapping
    public ResponseEntity<List<UserContactDto>> readAll() {

        log.info("readAllUserContacts: ");

        return ResponseEntity.status(HttpStatus.OK)
                .body(userContactService.readAll());
    }

    @GetMapping("/{userInfoId}")
    public ResponseEntity<UserContactDto> get(@PathVariable Integer userInfoId) {

        log.info("getUserContact: userInfoId = {}", userInfoId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userContactService.getById(userInfoId));
    }

    @PutMapping("/{userInfoId}")
    public ResponseEntity<UserContactDto> update(@RequestBody UserContactUpdateDto userContactUpdateDto,
                                                 @PathVariable Integer userInfoId) {

        log.info("updateContact: userContactUpdateDto = {}, userId = {}", userContactUpdateDto, userInfoId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userContactService.update(userInfoId, userContactUpdateDto));
    }

    @DeleteMapping("/{userInfoId}")
    public ResponseEntity<Void> delete(@PathVariable Integer userInfoId) {

        log.info("deleteContact: userInfoId = {}", userInfoId);

        userContactService.delete(userInfoId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
