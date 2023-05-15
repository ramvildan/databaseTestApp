package com.javatest.databaseTestApp.converter;

import com.javatest.databaseTestApp.dto.UserContactDto;
import com.javatest.databaseTestApp.entity.UserDetails;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UserContactConverter {

    public UserContactDto fromUserDetailsToUserContactDto(UserDetails userDetails) {

        if(isNull(userDetails)) {
            return null;
        }

        return UserContactDto.builder()
                .id(userDetails.getId())
                .surname(userDetails.getSurname())
                .name(userDetails.getName())
                .patronymic(userDetails.getPatronymic())
                .phoneNumber(userDetails.getPhoneNumber())
                .build();
    }
}
