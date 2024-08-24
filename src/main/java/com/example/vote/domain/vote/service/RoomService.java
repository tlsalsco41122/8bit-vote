package com.example.vote.domain.vote.service;

import com.example.vote.domain.vote.entity.Room;
import com.example.vote.domain.vote.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    // 방 생성
    public Room createRoom(String title, String contents) {
        if (title == null || title.isEmpty() || contents == null || contents.isEmpty()) {
            throw new IllegalArgumentException("제목이나 내용이 비어있습니다.");
        }
        Room room = Room.builder()
                .title(title)
                .contents(contents).build();
        return roomRepository.save(room);
    }

    // 모든 방 조회
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // 특정 방 조회
    public Room getRoom(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("방 조회에 실패했습니다."));
    }
}
