package com.example.vote.domain.vote.service;

import com.example.vote.domain.vote.dto.RoomRequest;
import com.example.vote.domain.vote.dto.RoomResponse;
import com.example.vote.domain.vote.entity.Room;
import com.example.vote.domain.vote.repository.RoomRepository;
import com.example.vote.domain.vote.repository.UserSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserSessionHolder userSessionHolder;

    // 방 생성
    public void createRoom(String title, String contents) {
        if (title == null || title.isEmpty() || contents == null || contents.isEmpty()) {
            throw new IllegalArgumentException("제목이나 내용이 비어있습니다.");
        }
        Room room = Room.builder()
                .title(title)
                .contents(contents)
                .username(userSessionHolder.getUser()).build();
        roomRepository.save(room);
    }

    // 모든 방 조회
    public List<RoomRequest> getAllRooms() {
        return roomRepository.findAll().stream().map(RoomRequest::of).toList();
    }

    // 특정 방 조회
    public RoomResponse getRoom(Long id) {
        System.out.println(".");
        return RoomResponse.of(roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("방 조회에 실패했습니다.")));
    }

    // 방 삭제
    public void deleteRoom(Long id){
        System.out.println("d");
        roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("방 삭제에 실피했습니다."));
    }
}
