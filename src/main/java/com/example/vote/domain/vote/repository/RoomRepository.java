package com.example.vote.domain.vote.repository;

import com.example.vote.domain.vote.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
