package com.schoolManagement.StudentManagemet.service.impl;

import com.schoolManagement.StudentManagemet.persistence.entity.RefreshToken;
import com.schoolManagement.StudentManagemet.persistence.repository.RefreshTokenRepository;
import com.schoolManagement.StudentManagemet.security.JwtUtil;
import com.schoolManagement.StudentManagemet.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {


    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtUtil jwtUtil;

    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, JwtUtil jwtUtil) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(jwtUtil.generateRefreshToken(username));
        refreshToken.setUsername(username);
        refreshToken.setExpiryDate(Instant.now().plus(1, ChronoUnit.DAYS));
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public boolean validateRefreshToken(String token) {
        return findByToken(token)
                .map(refreshToken -> !refreshToken.getExpiryDate().isBefore(Instant.now()))
                .orElse(false);
    }

}
