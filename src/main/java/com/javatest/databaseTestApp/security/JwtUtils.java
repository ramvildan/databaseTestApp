package com.javatest.databaseTestApp.security;

import com.javatest.databaseTestApp.entity.type.Role;
import com.javatest.databaseTestApp.security.dto.JwtAuthentication;
import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {

        JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setLogin(claims.getSubject());

        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {

        List<String> roles = List.of(claims.get("roles", String.class));

        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
