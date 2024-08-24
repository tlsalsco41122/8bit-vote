package com.example.vote.domain.user.controller;

import com.example.vote.domain.user.jwt.JWTUtil;
import com.example.vote.domain.user.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final JoinService joinService;
    private final JWTUtil jwtUtil;

//    public UserController(JoinService joinService, JWTUtil jwtUtil) {
//        this.joinService = joinService;
//        this.jwtUtil = jwtUtil;
//    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String authorization) {

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.split(" ")[1];
//            jwtUtil.blacklistToken(token); // 블랙리스트에 추가
        }
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestHeader("Authorization") String authorization) {

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.split(" ")[1];
            String username = jwtUtil.getUsername(token); // 현재 사용자 이름 추출
            joinService.deleteUser(username); // 사용자 삭제
        }
    }
}