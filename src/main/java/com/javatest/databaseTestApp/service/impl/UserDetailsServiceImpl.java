package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.converter.UserDetailsConverter;
import com.javatest.databaseTestApp.dto.UserDetailsCreateDto;
import com.javatest.databaseTestApp.dto.UserDetailsDto;
import com.javatest.databaseTestApp.dto.UserDetailsUpdateDto;
import com.javatest.databaseTestApp.entity.UserDetails;
import com.javatest.databaseTestApp.exception.UserNotFoundException;
import com.javatest.databaseTestApp.repository.UserDetailsRepository;
import com.javatest.databaseTestApp.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsConverter userDetailsConverter;

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetailsDto create(UserDetailsCreateDto userDetailsCreateDto) {

        UserDetails newUserDetails = UserDetails.builder()
                .surname(userDetailsCreateDto.getSurname())
                .name(userDetailsCreateDto.getName())
                .patronymic(userDetailsCreateDto.getPatronymic())
                .email(userDetailsCreateDto.getEmail())
                .phoneNumber(userDetailsCreateDto.getPhoneNumber())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        return userDetailsConverter.fromUserDetailsToUserDetailsDto(
                userDetailsRepository.save(newUserDetails)
        );
    }

    @Override
    public List<UserDetailsDto> readAll() {
        return userDetailsRepository.findAllByIsDeletedIsFalse().stream()
                .map(userDetailsConverter::fromUserDetailsToUserDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsDto update(Integer userId, UserDetailsUpdateDto userDetailsUpdateDto) {

        UserDetails userDetailsToUpdate = userDetailsRepository
                .findByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userDetailsToUpdate.setSurname(userDetailsUpdateDto.getSurname());
        userDetailsToUpdate.setName(userDetailsUpdateDto.getName());
        userDetailsToUpdate.setPatronymic(userDetailsUpdateDto.getPatronymic());
        userDetailsToUpdate.setBirthday(userDetailsUpdateDto.getBirthday());
        userDetailsToUpdate.setPhoneNumber(userDetailsUpdateDto.getPhoneNumber());
        userDetailsToUpdate.setEmail(userDetailsUpdateDto.getEmail());
        userDetailsToUpdate.setUpdatedAt(new Date());

        return userDetailsConverter.fromUserDetailsToUserDetailsDto(
                userDetailsRepository.save(userDetailsToUpdate));
    }

    @Override
    public void delete(Integer userId) {

        UserDetails userDetailsToDelete = userDetailsRepository
                .findByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userDetailsToDelete.setIsDeleted(true);
        userDetailsToDelete.setUpdatedAt(new Date());

        userDetailsConverter.fromUserDetailsToUserDetailsDto(
                userDetailsRepository.save(userDetailsToDelete)
        );
    }
}
