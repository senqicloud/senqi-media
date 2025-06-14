package com.senqicloud.senqimediaserver.config;

import org.springframework.util.AntPathMatcher;

public class SecurityWhiteList {

    // 白名单 URL
    public static final String[] PATHS = {
        "/user/login", "/user/register", "/images/upload",
    };

    // Ant 风格的路径匹配器
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    // 判断是否在白名单内
    public static boolean isWhitelisted(String requestPath) {
        for (String pattern : SecurityWhiteList.PATHS) {
            if (pathMatcher.match(pattern, requestPath)) {
                return true;
            }
        }
        return false;
    }
}
