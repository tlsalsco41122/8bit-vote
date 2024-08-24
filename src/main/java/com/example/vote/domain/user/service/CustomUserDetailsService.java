package com.example.vote.domain.user.service;

import com.example.vote.domain.user.dto.CustomUserDetails;
import com.example.vote.domain.user.entity.UserEntity;
import com.example.vote.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity != null) {
            return new CustomUserDetails(userEntity);
        }

        throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
    }
}
