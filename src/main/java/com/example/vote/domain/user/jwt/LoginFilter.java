////package com.example.vote.domain.user.jwt;
////
////import com.example.vote.domain.user.dto.CustomUserDetails;
////import jakarta.servlet.FilterChain;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.AuthenticationException;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////import java.util.Collection;
////import java.util.Iterator;
////
////public class LoginFilter extends UsernamePasswordAuthenticationFilter {
////
////    private final AuthenticationManager authenticationManager;
////    private final JWTUtil jwtUtil;
////
////    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
////        this.authenticationManager = authenticationManager;
////        this.jwtUtil = jwtUtil;
////    }
////
////    @Override
////    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
////        String username = obtainUsername(request);
////        String password = obtainPassword(request);
////
////        // 사용자 이름과 비밀번호로 인증 시도
////        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
////
////        return authenticationManager.authenticate(authToken);
////    }
////
////    @Override
////    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
////
////        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
////
////        String username = customUserDetails.getUsername();
////
////        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
////        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
////        GrantedAuthority auth = iterator.next();
////
////        String role = auth.getAuthority();
////
////        // JWT 생성 및 응답 헤더에 추가
////        String token = jwtUtil.createJwt(username, role, 60*60*10L);
////
////        response.addHeader("Authorization", "Bearer " + token);
////    }
////
////    @Override
////    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
////        // 인증 실패 시 응답 상태 설정
////        response.setStatus(401);
////    }
////}
////package com.example.vote.domain.user.jwt;
////
////import com.example.vote.domain.user.dto.CustomUserDetails;
////import jakarta.servlet.FilterChain;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.AuthenticationException;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////
////import java.io.IOException;
////import java.util.Collection;
////import java.util.Iterator;
////
////public class LoginFilter extends UsernamePasswordAuthenticationFilter {
////
////    private final AuthenticationManager authenticationManager;
////    private final JWTUtil jwtUtil;
////
////    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
////        this.authenticationManager = authenticationManager;
////        this.jwtUtil = jwtUtil;
////    }
////
////    @Override
////    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
////        String username = obtainUsername(request);
////        String password = obtainPassword(request);
////
////        // 사용자 이름과 비밀번호로 인증 시도
////        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
////        return authenticationManager.authenticate(authToken);
////    }
////
////    @Override
////    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
////        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
////
////        String username = customUserDetails.getUsername();
////
////        // 사용자 권한 추출
////        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
////        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
////        GrantedAuthority auth = iterator.hasNext() ? iterator.next() : null;
////
////        String role = auth != null ? auth.getAuthority() : "ROLE_USER";
////
////        // JWT 생성 및 응답 헤더에 추가
////        String token = jwtUtil.createJwt(username, role, 60 * 60 * 10L); // 10시간 유효
////
////        response.setHeader("Authorization", "Bearer " + token);
////
////        // 필터 체인 계속 실행
////        chain.doFilter(request, response);
////    }
////
////    @Override
////    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
////        // 인증 실패 시 응답 상태 설정 및 메시지 설정
////        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////        response.getWriter().write("Authentication failed: " + failed.getMessage());
////    }
////}
//package com.example.vote.domain.user.jwt;
//
//import com.example.vote.domain.user.dto.CustomUserDetails;
//import com.example.vote.domain.user.dto.User;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.Iterator;
//
//public class LoginFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    private final JWTUtil jwtUtil;
//
//    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String username = obtainUsername(request);
//        String password = obtainPassword(request);
//
//        // 사용자 이름과 비밀번호로 인증 시도
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
//        return authenticationManager.authenticate(authToken);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//        User user = customUserDetails.getUser();
//
//        String username = user.getUsername();
//
//        // 사용자 권한 추출
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//        GrantedAuthority auth = iterator.hasNext() ? iterator.next() : null;
//
//        String role = auth != null ? auth.getAuthority() : "ROLE_USER";
//
//        // JWT 생성 및 응답 헤더에 추가
//        String token = jwtUtil.createJwt(username, role, 60 * 60 * 10L); // 10시간 유효
//
//        response.setHeader("Authorization", "Bearer " + token);
//
//        // 필터 체인 계속 실행
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        // 인증 실패 시 응답 상태 설정 및 메시지 설정
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().write("Authentication failed: " + failed.getMessage());
//    }
//}