package com.example.vote.domain.user.jwt;

import com.example.vote.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        System.out.println(token);

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                System.out.println(token);
                if (!jwtUtil.isExpired(token)) {
                    System.out.println("1");
                    String username = jwtUtil.getUsername(token);
                    System.out.println("2");
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    System.out.println("3");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("4");
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("유효하지 않거나 만료된 토큰");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
