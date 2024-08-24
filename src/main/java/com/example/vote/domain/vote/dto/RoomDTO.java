package com.example.vote.domain.vote.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDTO {

    @NotEmpty(message = "제목을 반드시 입력하세요.")
    @Size(min = 1, max = 100, message = "제목은 1 ~ 100자 까지 가능합니다.")
    private String title;

    @NotEmpty(message = "내용을 반드시 입력하세요.")
    @Size(min = 1, max = 500, message = "내용은 1자 ~ 500자 까지 가능합니다.")
    private String contents;
}
