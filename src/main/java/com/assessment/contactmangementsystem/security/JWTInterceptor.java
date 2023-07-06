package com.assessment.contactmangementsystem.security;

import com.assessment.contactmangementsystem.errors.Unauthorized;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Autowired
    JWTService jwtService;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = request.getHeader("auth-token");
        if (!jwtService.verify(authToken)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            String json = mapper.writeValueAsString(Unauthorized.builder()
                    .status(401)
                    .message("Unauthorized Access!!")
                    .build());
            response.getOutputStream().write(json.getBytes());
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
