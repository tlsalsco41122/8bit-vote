package com.example.vote.config;

import com.example.vote.domain.user.jwt.JWTFilter;
import com.example.vote.domain.user.jwt.JWTUtil;
import com.example.vote.domain.user.service.CustomUserDetailsService;
import com.example.vote.domain.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JWTUtil jwtUtil, UserRepository userRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter(jwtUtil, userRepository);  // 필요한 매개변수들을 전달하여 JWTFilter 빈 생성
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/join", "/login").permitAll()  // 인증이 필요 없이 ㄱㄴ
                                .requestMatchers("/rooms/**", "/votes/**").hasRole("USER")  // 인증된 사용자만 ㄱㄴ
                                .anyRequest().authenticated()
                )

                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 세션 사용 안함
                )
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);  // JWT 필터 추가
        return http.build();
    }
}