package com.javatest.databaseTestApp.converter;

import com.javatest.databaseTestApp.dto.UserDto;
import com.javatest.databaseTestApp.entity.User;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UserConverter {

    public UserDto fromUserToUserDto(User user) {

        if (isNull(user)) {
            return null;
        }

        return UserDto.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
