package com.senqicloud.senqipicserver.controller;

import com.senqicloud.senqipicserver.enums.UserActionType;
import com.senqicloud.senqipicserver.exception.ValidateException;
import com.senqicloud.senqipicserver.model.request.UserLoginRequest;
import com.senqicloud.senqipicserver.model.request.UserRegisterRequest;
import com.senqicloud.senqipicserver.model.response.UserLoginResponse;
import com.senqicloud.senqipicserver.model.response.UserRegisterResponse;
import com.senqicloud.senqipicserver.service.JwtTokenService;
import com.senqicloud.senqipicserver.service.UserService;
import com.senqicloud.senqipicserver.strategy.LoginStrategy;
import com.senqicloud.senqipicserver.strategy.LoginStrategyFactory;
import com.senqicloud.senqipicserver.utils.RedisKeyUtils;
import com.senqicloud.senqipicserver.utils.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private LoginStrategyFactory loginStrategyFactory;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 1. 校验图形验证码是否正确
        // 根据 图形验证码ID + 图形验证码校验
        String redisCaptchaCode = redisUtils.get(RedisKeyUtils.getImageCaptchaKey(userRegisterRequest.getCaptchaId())).toString();

        if (!userRegisterRequest.getCaptchaCode().equals(redisCaptchaCode)) {
            throw new ValidateException("图形验证码错误！");
        }

        // 2. 判断邮箱或者短信验证码是否正确
        String redisEmailCode = redisUtils.get(RedisKeyUtils.getEmailCaptchaKey(UserActionType.REGISTER, userRegisterRequest.getEmail())).toString();

        if (!userRegisterRequest.getCode().equals(redisEmailCode)) {
            throw new ValidateException("验证码错误！");
        }

        // 3. 执行注册业务
        return userService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    public UserLoginResponse login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        // 1. 获取对应的登录策略
        LoginStrategy strategy = loginStrategyFactory.getStrategy(userLoginRequest.getLoginType());

        // 2. 调用实际的登录方法
        return strategy.login(userLoginRequest);

    }

    // 退出登录
    @PostMapping("/logout")
    public String login(HttpServletRequest request) {
        // TODO 从 Redis 中删除 JWT Token ID
        String authHeader = request.getHeader("Authorization");
        String token = "";
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // 去掉 "Bearer " 前缀
            // 这里的 token 就是 JWT 字符串
        }

        return userService.logout(token) ? "退出成功！" : "退出失败！";
    }

}
