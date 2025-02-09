package com.schoolManagement.StudentManagemet.controller;

import com.schoolManagement.StudentManagemet.dto.JwtResponse;
import com.schoolManagement.StudentManagemet.dto.UserDTO;
import com.schoolManagement.StudentManagemet.security.JwtUtil;
import com.schoolManagement.StudentManagemet.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailService userDetailService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailService userDetailService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
       return userDetailService.registerUser(userDTO);
    }

    @PostMapping(value = "/login", params = {"userName", "password"})
    public JwtResponse login(@RequestParam("userName") String username, @RequestParam("password") String password) {
        try {
            log.info("hitted");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            return JwtResponse.builder()
                    .access_token(jwtUtil.generateToken(authentication))
                    .refresh_token(jwtUtil.generateToken(authentication))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Invalid User Credentials");
        }
    }
}
