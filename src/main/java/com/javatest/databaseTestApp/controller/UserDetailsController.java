package com.javatest.databaseTestApp.controller;

import com.javatest.databaseTestApp.dto.UserDetailsCreateDto;
import com.javatest.databaseTestApp.dto.UserDetailsDto;
import com.javatest.databaseTestApp.dto.UserDetailsUpdateDto;
import com.javatest.databaseTestApp.service.UserDetailsService;
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
@RequestMapping("users/details")
@RequiredArgsConstructor
@Log4j2
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

//    @PreAuthorize("hasAuthority('APP_USER')")
    @PostMapping("/create")
    public ResponseEntity<UserDetailsDto> create(@RequestBody UserDetailsCreateDto userDetailsCreateDto) {

        log.info("createUser: userDetailsCreateDto = {}", userDetailsCreateDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userDetailsService.create(userDetailsCreateDto));
    }

//    @PreAuthorize("hasAuthority('APP_USER')")
    @GetMapping
    public ResponseEntity<List<UserDetailsDto>> findAll() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(userDetailsService.readAll());
    }

//    @PreAuthorize("hasAuthority('APP_USER')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDetailsDto> update(@RequestBody UserDetailsUpdateDto userDetailsUpdateDto,
                                            @PathVariable Integer userId) {

        log.info("updateUser: userDetailsUpdateDto = {}, userId = {}", userDetailsUpdateDto, userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userDetailsService.update(userId, userDetailsUpdateDto));
    }

//    @PreAuthorize("hasAuthority('APP_USER')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Integer userId) {

        log.info("deleteUser: userId = {}", userId);

        userDetailsService.delete(userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
