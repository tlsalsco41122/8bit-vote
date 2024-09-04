package com.example.vote.domain.vote.service;

import com.example.vote.domain.vote.entity.Room;
import com.example.vote.domain.vote.entity.Vote;
import com.example.vote.domain.vote.repository.RoomRepository;
import com.example.vote.domain.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final RoomRepository roomRepository;

    public void createVote(Long roomId, boolean choice) {
        System.out.println("v1");
        // 특정 방에 대한 투표 생성: 방을 조회하고, 투표를 빌더 패턴으로 생성 후 저장
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("방 조회에 실패했습니다."));

        Vote vote = Vote.builder()
                .choice(choice)
                .room(room).build();
        System.out.println("v2");

        voteRepository.save(vote);
        System.out.println("v4");
    }

    // 예 투표 수 계산
    public long countYesVotes(Long roomId) {
        return voteRepository.countByRoomIdAndChoice(roomId, true);
    }

    // 아니오 투표 수 계산
    public long countNoVotes(Long roomId) {
        return voteRepository.countByRoomIdAndChoice(roomId, false);
    }
}
