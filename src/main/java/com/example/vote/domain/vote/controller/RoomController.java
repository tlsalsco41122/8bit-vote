package com.example.vote.domain.vote.controller;

import com.example.vote.domain.vote.dto.RoomRequest;
import com.example.vote.domain.vote.dto.RoomResponse;
import com.example.vote.domain.vote.entity.Room;
import com.example.vote.domain.vote.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "*")  // 모든 출처 허용
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    // 방 생성
    @PostMapping
    public void createRoom(@Valid @RequestBody Room room) {
        roomService.createRoom(room.getTitle(),room.getContents());
    }

    // 모든 방 조회
    @GetMapping()
    public List<RoomRequest> getAllRooms() {
        return roomService.getAllRooms();
    }

    // 특정 방 조회
    @GetMapping("/{id}")
    public RoomResponse getRoom(@PathVariable(name="id") Long id) {
         return roomService.getRoom(id);
    }

    // 방 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteRoom(@PathVariable(name = "id") Long id){
        roomService.deleteRoom(id);
    }
}

