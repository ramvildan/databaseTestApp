package com.javatest.databaseTestApp.converter;

import com.javatest.databaseTestApp.dto.UserDetailsDto;
import com.javatest.databaseTestApp.entity.UserDetails;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UserDetailsConverter {

    public UserDetailsDto fromUserDetailsToUserDetailsDto(UserDetails userDetails) {

        if(isNull(userDetails)) {
            return null;
        }

        return UserDetailsDto.builder()
                .id(userDetails.getId())
                .surname(userDetails.getSurname())
                .name(userDetails.getName())
                .patronymic(userDetails.getPatronymic())
                .email(userDetails.getEmail())
                .phoneNumber(userDetails.getPhoneNumber())
                .build();
    }
}
