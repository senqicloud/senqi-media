package com.senqicloud.senqipicserver.filter;

import com.senqicloud.senqipicserver.service.JwtTokenService;
import com.senqicloud.senqipicserver.utils.RedisKeyUtils;
import com.senqicloud.senqipicserver.utils.RedisUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 获取 JWT Token
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            // 实际的 JWT Token
            String token = authHeader.substring(7);
            String jwtTokenId = jwtTokenService.getJwtTokenId(token);

            // 2. 校验 Token
            if (!jwtTokenService.validateToken(token) || redisUtils.get(RedisKeyUtils.getJwtTokenKey(jwtTokenId)) != null) {
                // Token 校验错误 或者 黑名单中存在则都校验登录失败，直接被拦截
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // 3. 放行
            filterChain.doFilter(request, response);
        }

    }
}
