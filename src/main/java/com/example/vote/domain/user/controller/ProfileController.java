package com.example.vote.domain.user.controller;

import com.example.vote.domain.user.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final JWTUtil jwtUtil;

    @GetMapping("/profile")
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof String) {
            String username = (String) authentication.getPrincipal();

            return username;
        }

        throw new RuntimeException("no");
    }
}