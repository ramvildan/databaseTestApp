package com.javatest.databaseTestApp.controller;

import com.javatest.databaseTestApp.dto.UserContactCreateDto;
import com.javatest.databaseTestApp.dto.UserContactDto;
import com.javatest.databaseTestApp.dto.UserContactUpdateDto;
import com.javatest.databaseTestApp.service.UserContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAuthority('APP_USER')")
    @PostMapping("/create/{userId}")
    public ResponseEntity<UserContactDto> create(@RequestBody @Valid UserContactCreateDto userContactCreateDto,
                                                 @PathVariable Integer userId) {

        log.info("createContact: userDetailsCreateDto = {}", userContactCreateDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userContactService.create(userId, userContactCreateDto));
    }

    @PreAuthorize("hasAuthority('APP_USER')")
    @GetMapping
    public ResponseEntity<List<UserContactDto>> readAll() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(userContactService.readAll());
    }

    @PreAuthorize("hasAuthority('APP_USER')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserContactDto> update(@RequestBody @Valid UserContactUpdateDto userContactUpdateDto,
                                                 @PathVariable Integer userId) {

        log.info("updateContact: userContactUpdateDto = {}, userId = {}", userContactUpdateDto, userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userContactService.update(userId, userContactUpdateDto));
    }

    @PreAuthorize("hasAuthority('APP_USER')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Integer userId) {

        log.info("deleteContact: userId = {}", userId);

        userContactService.delete(userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
