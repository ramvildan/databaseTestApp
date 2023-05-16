package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.converter.UserInfoConverter;
import com.javatest.databaseTestApp.dto.UserInfoCreateDto;
import com.javatest.databaseTestApp.dto.UserInfoDto;
import com.javatest.databaseTestApp.dto.UserInfoUpdateDto;
import com.javatest.databaseTestApp.entity.UserInfo;
import com.javatest.databaseTestApp.exception.UserInfoNotFoundException;
import com.javatest.databaseTestApp.repository.UserInfoRepository;
import com.javatest.databaseTestApp.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoConverter userInfoConverter;

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserInfoDto create(UserInfoCreateDto userInfoCreateDto) {

        UserInfo newUserInfo = UserInfo.builder()
                .surname(userInfoCreateDto.getSurname())
                .name(userInfoCreateDto.getName())
                .patronymic(userInfoCreateDto.getPatronymic())
                .birthday(userInfoCreateDto.getBirthday())
                .email(userInfoCreateDto.getEmail())
                .phoneNumber(userInfoCreateDto.getPhoneNumber())
                .createdAt(new Date())
                .updatedAt(new Date())
                .isDeleted(false)
                .build();

        return userInfoConverter.fromUserInfoToUserInfoDto(
                userInfoRepository.save(newUserInfo)
        );
    }

    @Override
    public List<UserInfoDto> readAll() {
        return userInfoRepository.findAllByIsDeletedIsFalse().stream()
                .map(userInfoConverter::fromUserInfoToUserInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserInfoDto getById(Integer userInfoId) {
        return userInfoConverter.fromUserInfoToUserInfoDto(
                userInfoRepository.findByIdAndIsDeletedIsFalse(userInfoId)
                        .orElseThrow(() -> new UserInfoNotFoundException(userInfoId)));
    }

    @Override
    public UserInfoDto update(Integer userInfoId, UserInfoUpdateDto userInfoUpdateDto) {

        UserInfo userInfoToUpdate = userInfoRepository
                .findByIdAndIsDeletedIsFalse(userInfoId)
                .orElseThrow(() -> new UserInfoNotFoundException(userInfoId));

        userInfoToUpdate.setSurname(userInfoUpdateDto.getSurname());
        userInfoToUpdate.setName(userInfoUpdateDto.getName());
        userInfoToUpdate.setPatronymic(userInfoUpdateDto.getPatronymic());
        userInfoToUpdate.setBirthday(userInfoUpdateDto.getBirthday());
        userInfoToUpdate.setPhoneNumber(userInfoUpdateDto.getPhoneNumber());
        userInfoToUpdate.setEmail(userInfoUpdateDto.getEmail());
        userInfoToUpdate.setUpdatedAt(new Date());

        return userInfoConverter.fromUserInfoToUserInfoDto(
                userInfoRepository.save(userInfoToUpdate));
    }

    @Override
    public void delete(Integer userInfoId) {

        UserInfo userInfoToDelete = userInfoRepository
                .findByIdAndIsDeletedIsFalse(userInfoId)
                .orElseThrow(() -> new UserInfoNotFoundException(userInfoId));

        userInfoToDelete.setIsDeleted(true);
        userInfoToDelete.setUpdatedAt(new Date());

        userInfoConverter.fromUserInfoToUserInfoDto(
                userInfoRepository.save(userInfoToDelete)
        );
    }
}
