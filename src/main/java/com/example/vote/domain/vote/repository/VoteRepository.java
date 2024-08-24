package com.example.vote.domain.vote.repository;

import com.example.vote.domain.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    // 방에 각각 투표 수를 계산하기 위해 추가
    long countByRoomIdAndChoice(Long roomId, boolean choice);

    // 모든 투표 삭제하기 위해 추가
    void deleteAllByRoomId(Long roomId);
}
