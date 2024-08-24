package com.example.vote.domain.vote.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteDTO {

    private boolean choice;
    private Long roomId;
}
