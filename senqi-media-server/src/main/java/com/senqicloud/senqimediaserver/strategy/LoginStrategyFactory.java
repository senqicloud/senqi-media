package com.senqicloud.senqimediaserver.strategy;

import com.senqicloud.senqimediaserver.enums.LoginType;
import com.senqicloud.senqimediaserver.exception.ServerErrorException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginStrategyFactory {

    private final List<LoginStrategy> strategies;

    // 所有实现了 LoginStrategy 的 Bean 会自动注入
    public LoginStrategyFactory(List<LoginStrategy> strategies) {
        this.strategies = strategies;
    }

    public LoginStrategy getStrategy(LoginType loginType) {
        return strategies.stream()
                .filter(s -> s.supports(loginType))
                .findFirst()
                .orElseThrow(() -> new ServerErrorException("不支持的登录方式"));
    }
}