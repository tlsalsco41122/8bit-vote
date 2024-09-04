package com.example.vote.domain.vote.repository;

import com.example.vote.domain.user.dto.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserSessionHolderImpl implements UserSessionHolder {

    @Override
    public String getUser(){
        return ((CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
    }
}
