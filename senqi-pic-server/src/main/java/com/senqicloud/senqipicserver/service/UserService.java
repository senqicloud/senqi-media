package com.senqicloud.senqipicserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senqicloud.senqipicserver.model.entity.User;
import com.senqicloud.senqipicserver.model.request.UserRegisterRequest;
import com.senqicloud.senqipicserver.model.response.UserRegisterResponse;

public interface UserService extends IService<User> {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
}
