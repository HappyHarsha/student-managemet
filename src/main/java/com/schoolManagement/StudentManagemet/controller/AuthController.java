package com.schoolManagement.StudentManagemet.controller;

import com.schoolManagement.StudentManagemet.dto.JwtResponse;
import com.schoolManagement.StudentManagemet.dto.UserDTO;
import com.schoolManagement.StudentManagemet.persistence.entity.RefreshToken;
import com.schoolManagement.StudentManagemet.security.CustomUserDetailsService;
import com.schoolManagement.StudentManagemet.security.JwtUser;
import com.schoolManagement.StudentManagemet.security.JwtUtil;
import com.schoolManagement.StudentManagemet.service.RefreshTokenService;
import com.schoolManagement.StudentManagemet.service.SchUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final SchUserDetailsService userDetailService;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenService refreshTokenService;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, SchUserDetailsService userDetailService, CustomUserDetailsService customUserDetailsService, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
        this.customUserDetailsService = customUserDetailsService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
       return userDetailService.registerUser(userDTO);
    }

    @PostMapping(value = "/login", params = {"userName", "password"})
    public ResponseEntity<JwtResponse> login(@RequestParam("userName") String username, @RequestParam("password") String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            JwtUser userDetails = (JwtUser) authentication.getPrincipal();
            String accessToken = jwtUtil.generateToken(userDetails);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

            JwtResponse jwtResponse =  JwtResponse.builder()
                    .access_token(accessToken)
                    .refresh_token(refreshToken.getToken())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);

        } catch (Exception e) {
            throw new RuntimeException("Invalid User Credentials");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@RequestParam String refreshToken) {
        Optional<RefreshToken> storedToken = refreshTokenService.findByToken(refreshToken);

        if (storedToken.isPresent() && refreshTokenService.validateRefreshToken(refreshToken)) {

            JwtUser userDetails = (JwtUser) customUserDetailsService.loadUserByUsername(storedToken.get().getUsername());

            String newAccessToken = jwtUtil.generateToken(userDetails);
            String refreshToken1 = jwtUtil.generateRefreshToken(userDetails.getUsername());

            JwtResponse jwtResponse = JwtResponse.builder()
                    .access_token(newAccessToken)
                    .refresh_token(refreshToken1)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
