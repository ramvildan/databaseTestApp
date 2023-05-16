package com.javatest.databaseTestApp.converter;

import com.javatest.databaseTestApp.dto.UserInfoDto;
import com.javatest.databaseTestApp.entity.UserInfo;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UserInfoConverter {

    public UserInfoDto fromUserInfoToUserInfoDto(UserInfo userInfo) {

        if (isNull(userInfo)) {
            return null;
        }

        return UserInfoDto.builder()
                .id(userInfo.getId())
                .surname(userInfo.getSurname())
                .name(userInfo.getName())
                .patronymic(userInfo.getPatronymic())
                .birthday(userInfo.getBirthday())
                .email(userInfo.getEmail())
                .phoneNumber(userInfo.getPhoneNumber())
                .build();
    }
}
