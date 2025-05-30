package com.senqicloud.senqimediaserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senqicloud.senqimediaserver.model.entity.User;
import com.senqicloud.senqimediaserver.model.request.UserLoginRequest;
import com.senqicloud.senqimediaserver.model.request.UserRegisterRequest;
import com.senqicloud.senqimediaserver.model.response.UserLoginResponse;
import com.senqicloud.senqimediaserver.model.response.UserRegisterResponse;

public interface UserService extends IService<User> {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

    UserLoginResponse loginByAccountAndPassword(UserLoginRequest userLoginRequest);

    UserLoginResponse loginBySmsCode(UserLoginRequest userLoginRequest);

    UserLoginResponse loginByEmailCode(UserLoginRequest userLoginRequest);

    boolean logout(String token);
}
