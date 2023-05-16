package com.javatest.databaseTestApp.security.filter;

import com.javatest.databaseTestApp.security.dto.JwtAuthentication;
import com.javatest.databaseTestApp.security.dto.JwtProvider;
import com.javatest.databaseTestApp.security.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import static java.util.Objects.isNull;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final String AUTHORISATION = "authorisation";

    private final JwtProvider jwtProvider;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String token = getTokenFromRequest((HttpServletRequest) servletRequest);

        if (!isNull(token) && jwtProvider.validateAccessToken(token)) {

            Claims claims = jwtProvider.getAccessClaims(token);
            JwtAuthentication jwtInfoToken = JwtUtils.generate(claims);
            jwtInfoToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {

        String bearer = request.getHeader(AUTHORISATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {

            return bearer.substring(7);
        }

        return null;
    }
}
