package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.converter.UserContactConverter;
import com.javatest.databaseTestApp.dto.UserContactCreateDto;
import com.javatest.databaseTestApp.dto.UserContactDto;
import com.javatest.databaseTestApp.dto.UserContactUpdateDto;
import com.javatest.databaseTestApp.entity.UserDetails;
import com.javatest.databaseTestApp.exception.UserNotFoundException;
import com.javatest.databaseTestApp.repository.UserDetailsRepository;
import com.javatest.databaseTestApp.service.UserContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserContactServiceImpl implements UserContactService {

    private final UserDetailsRepository userDetailsRepository;

    private final UserContactConverter userContactConverter;

    @Override
    public UserContactDto create(Integer userId, UserContactCreateDto userContactCreateDto) {

        UserDetails newUserContact = UserDetails.builder()
                .surname(userContactCreateDto.getSurname())
                .name(userContactCreateDto.getName())
                .patronymic(userContactCreateDto.getPatronymic())
                .phoneNumber(userContactCreateDto.getPhoneNumber())
                .createdAt(new Date())
                .updatedAt(new Date())
                .isDeleted(false)
                .build();

        return userContactConverter.fromUserDetailsToUserContactDto(
                userDetailsRepository.save(newUserContact)
        );
    }

    @Override
    public List<UserContactDto> readAll() {
        return userDetailsRepository.findAllByIsDeletedIsFalse().stream()
                .map(userContactConverter::fromUserDetailsToUserContactDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserContactDto update(Integer userId, UserContactUpdateDto userContactUpdateDto) {

        UserDetails userContactToUpdate = userDetailsRepository
                .findByIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userContactToUpdate.setSurname(userContactUpdateDto.getSurname());
        userContactToUpdate.setName(userContactUpdateDto.getName());
        userContactToUpdate.setPatronymic(userContactUpdateDto.getPatronymic());
        userContactToUpdate.setPhoneNumber(userContactUpdateDto.getPhoneNumber());
        userContactToUpdate.setUpdatedAt(new Date());

        return userContactConverter.fromUserDetailsToUserContactDto(
                userDetailsRepository.save(userContactToUpdate)
        );
    }

    @Override
    public void delete(Integer userId) {

        UserDetails userContactToDelete = userDetailsRepository
                .findByIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userContactToDelete.setIsDeleted(true);
        userContactToDelete.setUpdatedAt(new Date());

        userContactConverter.fromUserDetailsToUserContactDto(
                userDetailsRepository.save(userContactToDelete)
        );
    }
}
