package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.converter.UserContactConverter;
import com.javatest.databaseTestApp.dto.UserContactCreateDto;
import com.javatest.databaseTestApp.dto.UserContactDto;
import com.javatest.databaseTestApp.dto.UserContactUpdateDto;
import com.javatest.databaseTestApp.entity.UserInfo;
import com.javatest.databaseTestApp.exception.UserNotFoundException;
import com.javatest.databaseTestApp.repository.UserInfoRepository;
import com.javatest.databaseTestApp.service.UserContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserContactServiceImpl implements UserContactService {

    private final UserInfoRepository userInfoRepository;

    private final UserContactConverter userContactConverter;

    @Override
    public UserContactDto create(UserContactCreateDto userContactCreateDto) {

        UserInfo newUserContact = UserInfo.builder()
                .surname(userContactCreateDto.getSurname())
                .name(userContactCreateDto.getName())
                .patronymic(userContactCreateDto.getPatronymic())
                .phoneNumber(userContactCreateDto.getPhoneNumber())
                .createdAt(new Date())
                .updatedAt(new Date())
                .isDeleted(false)
                .build();

        return userContactConverter.fromUserDetailsToUserContactDto(
                userInfoRepository.save(newUserContact)
        );
    }

    @Override
    public List<UserContactDto> readAll() {
        return userInfoRepository.findAllByIsDeletedIsFalse().stream()
                .map(userContactConverter::fromUserDetailsToUserContactDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserContactDto update(Integer userId, UserContactUpdateDto userContactUpdateDto) {

        UserInfo userContactToUpdate = userInfoRepository
                .findByIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userContactToUpdate.setSurname(userContactUpdateDto.getSurname());
        userContactToUpdate.setName(userContactUpdateDto.getName());
        userContactToUpdate.setPatronymic(userContactUpdateDto.getPatronymic());
        userContactToUpdate.setPhoneNumber(userContactUpdateDto.getPhoneNumber());
        userContactToUpdate.setUpdatedAt(new Date());

        return userContactConverter.fromUserDetailsToUserContactDto(
                userInfoRepository.save(userContactToUpdate)
        );
    }

    @Override
    public void delete(Integer userId) {

        UserInfo userContactToDelete = userInfoRepository
                .findByIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userContactToDelete.setIsDeleted(true);
        userContactToDelete.setUpdatedAt(new Date());

        userContactConverter.fromUserDetailsToUserContactDto(
                userInfoRepository.save(userContactToDelete)
        );
    }
}
