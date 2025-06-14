package com.senqicloud.senqimediaserver.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senqicloud.senqimediaserver.config.SecurityWhiteList;
import com.senqicloud.senqimediaserver.response.Result;
import com.senqicloud.senqimediaserver.response.ResultCode;
import com.senqicloud.senqimediaserver.service.JwtTokenService;
import com.senqicloud.senqimediaserver.utils.RedisKeyUtils;
import com.senqicloud.senqimediaserver.utils.RedisUtils;
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
        // 0. 放行白名单
        String pathInfo = request.getRequestURI();

        // 判断是否在白名单内
        if (SecurityWhiteList.isWhitelisted(pathInfo)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 1. 获取 JWT Token
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            // 实际的 JWT Token
            String token = authHeader.substring(7);
            String jwtTokenId = jwtTokenService.getJwtTokenId(token);

            // 2. 校验 Token
            if (!jwtTokenService.validateToken(token) || redisUtils.get(RedisKeyUtils.getJwtTokenKey(jwtTokenId)) != null) {
                // Token 校验错误 或者 黑名单中存在则都校验登录失败，直接被拦截
                unauthorized(request, response);
            }

            // 3. 放行
            filterChain.doFilter(request, response);
        } else {
            unauthorized(request, response);
        }

    }

    /**
     *  认证失败响应处理
     * */
    public void unauthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
        response.setContentType("application/json;charset=utf-8");
        Result<String> result = new Result<>(ResultCode.UNAUTHORIZED);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);

        response.getWriter().write(json);
        response.getWriter().flush();
    }
}
