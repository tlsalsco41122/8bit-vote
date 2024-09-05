package com.example.vote.config;


import com.example.vote.domain.user.jwt.JWTFilter;
import com.example.vote.domain.user.jwt.JWTUtil;
import com.example.vote.domain.user.repository.UserRepository;
import com.example.vote.domain.user.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

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
        return http
                .csrf(AbstractHttpConfigurer::disable)  // CSRF 비활성화

                .authorizeHttpRequests((configurer) -> configurer
                        .requestMatchers(HttpMethod.POST, "/join", "/login").anonymous()
                        .requestMatchers(HttpMethod.POST, "/rooms/**", "/votes/**","/users/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/rooms/**", "/votes/**","/profile").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/rooms/**","/users/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                )

                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 세션 사용 안함
                )
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)  // JWT 필터 추가
         .build();
    }
}
