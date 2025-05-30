package com.senqicloud.senqimediaserver.service;

import com.senqicloud.senqimediaserver.model.entity.User;

public interface JwtTokenService {
    // 生成 JWT Token
    String generateToken(User user);

    // 校验 JWT Token
    boolean validateToken(String token);

    // 从 Token 中获取 UserName
    String getUsernameFromToken(String token);

    // 从 Token 中获取 UserID
    Long getUserIdFromToken(String token);

    // 获取 JWT Token ID
    String getJwtTokenId(String token);
}