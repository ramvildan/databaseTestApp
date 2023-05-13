package com.javatest.databaseTestApp.dto;

import com.javatest.databaseTestApp.entity.type.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String login;
    private String password;
    private Role role;
}
