package com.senqicloud.senqimediaserver.controller;

import com.senqicloud.senqimediaserver.enums.UserActionType;
import com.senqicloud.senqimediaserver.exception.ValidateException;
import com.senqicloud.senqimediaserver.model.request.UserLoginRequest;
import com.senqicloud.senqimediaserver.model.request.UserRegisterRequest;
import com.senqicloud.senqimediaserver.model.response.UserLoginResponse;
import com.senqicloud.senqimediaserver.model.response.UserRegisterResponse;
import com.senqicloud.senqimediaserver.service.JwtTokenService;
import com.senqicloud.senqimediaserver.service.UserService;
import com.senqicloud.senqimediaserver.strategy.LoginStrategy;
import com.senqicloud.senqimediaserver.strategy.LoginStrategyFactory;
import com.senqicloud.senqimediaserver.utils.RedisKeyUtils;
import com.senqicloud.senqimediaserver.utils.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 *  用户管理
 * */

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

    /**
     *  用户注册
     * */
    @PostMapping("/register")
    public UserRegisterResponse register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
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

    /**
     *  用户登录
     * */
    @PostMapping("/login")
    public UserLoginResponse login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        // 1. 获取对应的登录策略
        LoginStrategy strategy = loginStrategyFactory.getStrategy(userLoginRequest.getLoginType());

        // 2. 调用实际的登录方法
        return strategy.login(userLoginRequest);

    }

    /**
     *  退出登录
     */
    @PostMapping("/logout")
    public String login(HttpServletRequest request) {
        // 从 Redis 中删除 JWT Token ID
        String authHeader = request.getHeader("Authorization");
        String token = "";
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // 去掉 "Bearer " 前缀
        }

        return userService.logout(token) ? "退出成功！" : "退出失败！";
    }

}
