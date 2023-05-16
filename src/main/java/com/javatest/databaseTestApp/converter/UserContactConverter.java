package com.javatest.databaseTestApp.converter;

import com.javatest.databaseTestApp.dto.UserContactDto;
import com.javatest.databaseTestApp.entity.UserInfo;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UserContactConverter {

    public UserContactDto fromUserDetailsToUserContactDto(UserInfo userInfo) {

        if (isNull(userInfo)) {
            return null;
        }

        return UserContactDto.builder()
                .id(userInfo.getId())
                .surname(userInfo.getSurname())
                .name(userInfo.getName())
                .patronymic(userInfo.getPatronymic())
                .phoneNumber(userInfo.getPhoneNumber())
                .build();
    }
}
