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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Log4j2
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UserDetailsDto> createUserDetails(@RequestBody
                                            @Valid UserDetailsCreateDto userDetailsCreateDto) {

        log.info("createUser: userDetailsCreateDto = {}", userDetailsCreateDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userDetailsService.createUserDetails(userDetailsCreateDto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/all")
    public ResponseEntity<List<UserDetailsDto>> readAllUsersDetails() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(userDetailsService.readAllUsersDetails());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/details/update/{userId}")
    public ResponseEntity<UserDetailsDto> updateUserDetails(@RequestBody
                                            @Valid UserDetailsUpdateDto userDetailsUpdateDto,
                                            @PathVariable Integer userId) {

        log.info("updateUser: userDetailsUpdateDto = {}, userId = {}", userDetailsUpdateDto, userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(userDetailsService.updateUserDetails(userId, userDetailsUpdateDto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/details/delete/{userId}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Integer userId) {

        log.info("deleteUser: userId = {}", userId);

        userDetailsService.deleteUserDetails(userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}