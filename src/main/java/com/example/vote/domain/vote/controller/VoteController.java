package com.example.vote.domain.vote.controller;

import com.example.vote.domain.vote.dto.VoteDTO;
import com.example.vote.domain.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    // 투표 생성
    @PostMapping("/{roomId}")
    public void createVote(@PathVariable(name = "roomId") Long roomId, @RequestBody VoteDTO voteDTO) {
        voteService.createVote(roomId, voteDTO.isChoice());
    }

    // 투표 결과 조회
    @GetMapping("/{roomId}/results")
    public Map<String, Long> getResults(@PathVariable(name = "roomId") Long roomId) {

        long yesVotes = voteService.countYesVotes(roomId);
        long noVotes = voteService.countNoVotes(roomId);

        Map<String, Long> results = new HashMap<>();
        results.put("yes", yesVotes);
        results.put("no", noVotes);
        return results;
    }
}
