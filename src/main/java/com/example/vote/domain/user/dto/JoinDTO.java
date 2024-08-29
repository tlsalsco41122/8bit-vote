package com.example.vote.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDTO {

    private String username;
    private String password;
    private String confirmPassword; // 비번 재확인
}
