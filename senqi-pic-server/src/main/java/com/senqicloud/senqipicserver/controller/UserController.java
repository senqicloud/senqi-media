package com.senqicloud.senqipicserver.controller;

import com.senqicloud.senqipicserver.enums.RedisSceneKey;
import com.senqicloud.senqipicserver.exception.ValidateException;
import com.senqicloud.senqipicserver.model.request.UserRegisterRequest;
import com.senqicloud.senqipicserver.model.response.UserRegisterResponse;
import com.senqicloud.senqipicserver.service.UserService;
import com.senqicloud.senqipicserver.utils.RedisKeyUtils;
import com.senqicloud.senqipicserver.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest userRegisterRequest){
        // 1. 校验图形验证码是否正确
        // 根据 图形验证码ID + 图形验证码校验
        String redisCaptchaCode = redisUtils.get(RedisKeyUtils.getImageCaptchaKey(userRegisterRequest.getCaptchaId())).toString();

        if (!userRegisterRequest.getCaptchaCode().equals(redisCaptchaCode)){
            throw new ValidateException("图形验证码错误！");
        }

        // 2. 判断邮箱或者短信验证码是否正确
        String redisEmailCode = redisUtils.get(RedisKeyUtils.getEmailCaptchaKey(RedisSceneKey.REGISTER.get(), userRegisterRequest.getEmail())).toString();

        if (!userRegisterRequest.getCode().equals(redisEmailCode)){
            throw new ValidateException("验证码错误！");
        }

        // 3. 执行注册业务
        return userService.register(userRegisterRequest);
    }
}
