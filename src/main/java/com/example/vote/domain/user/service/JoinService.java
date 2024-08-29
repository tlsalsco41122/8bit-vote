package com.example.vote.domain.user.service;

import com.example.vote.domain.user.dto.JoinDTO;
import com.example.vote.domain.user.entity.UserEntity;
import com.example.vote.domain.user.repository.UserRepository;
import com.example.vote.domain.vote.repository.RoomRepository;  // 추가
import com.example.vote.domain.vote.repository.VoteRepository;  // 추가
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoomRepository roomRepository;
    private final VoteRepository voteRepository;

    public JoinService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoomRepository roomRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roomRepository = roomRepository;
        this.voteRepository = voteRepository;
    }

    // 회원가입
    public void joinProcess(JoinDTO joinDTO) {
        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setRole("ROLE_USER");  // 기본 역할 설정

        userRepository.save(userEntity);
    }

    // 회원탈퇴
    @Transactional
    public void deleteUser(String username) {

        UserEntity user = userRepository.findByUsername(username);

        if (user != null) {
            // 방과 투표 삭제
            roomRepository.findAll().forEach(room -> voteRepository.deleteAllByRoomId(room.getId()));
            roomRepository.deleteAll();
            userRepository.delete(user);
        }
    }
}
