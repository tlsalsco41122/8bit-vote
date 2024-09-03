package com.example.vote.domain.user.controller;

import com.example.vote.domain.user.dto.JoinDTO;
import com.example.vote.domain.user.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDTO joinDTO) {

        // 비밀번호와 비밀번호 확인 일치 여부 확인
        if (!joinDTO.getPassword().equals(joinDTO.getConfirmPassword())) {
            return "비밀번호가 일치하지 않습니다.";
        }

        joinService.joinProcess(joinDTO);

        return "ok";
    }
}