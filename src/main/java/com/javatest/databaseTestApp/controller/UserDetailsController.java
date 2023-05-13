package com.javatest.databaseTestApp.controller;

import com.javatest.databaseTestApp.dto.UserDetailsCreateDto;
import com.javatest.databaseTestApp.dto.UserDetailsDto;
import com.javatest.databaseTestApp.dto.UserDetailsUpdateDto;
import com.javatest.databaseTestApp.service.UserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    @PostMapping
    public UserDetailsDto createUserDetails(@RequestBody
                                            @Valid UserDetailsCreateDto userDetailsCreateDto) {

        log.info("createUser: userDetailsCreateDto = {}", userDetailsCreateDto);

        return userDetailsService.createUserDetails(userDetailsCreateDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<UserDetailsDto> readAllUsersDetails() {
        return userDetailsService.readAllUsersDetails();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{userId}")
    public UserDetailsDto updateUserDetails(@RequestBody
                                            @Valid UserDetailsUpdateDto userDetailsUpdateDto,
                                            @PathVariable Integer userId) {

        log.info("updateUser: userDetailsUpdateDto = {}, userId = {}", userDetailsUpdateDto, userId);

        return userDetailsService.updateUserDetails(userId, userDetailsUpdateDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{userId}")
    public void deleteUserDetails(@PathVariable Integer userId) {

        log.info("deleteUser: userId = {}", userId);

        userDetailsService.deleteUserDetails(userId);
    }
}
