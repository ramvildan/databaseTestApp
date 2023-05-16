package com.javatest.databaseTestApp.entity.type;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ROLE_USER("ROLE_USER");

    private final String vale;

    @Override
    public String getAuthority() {
        return vale;
    }
}
