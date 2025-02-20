package com.schoolManagement.StudentManagemet.service;

import com.schoolManagement.StudentManagemet.persistence.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(String username);

    Optional<RefreshToken> findByToken(String token);

    boolean validateRefreshToken(String token);

}
