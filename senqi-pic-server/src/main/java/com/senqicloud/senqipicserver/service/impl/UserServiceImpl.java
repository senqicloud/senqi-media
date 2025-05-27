package com.senqicloud.senqipicserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senqicloud.senqipicserver.exception.ServerErrorException;
import com.senqicloud.senqipicserver.exception.ValidateException;
import com.senqicloud.senqipicserver.mapper.UserMapper;
import com.senqicloud.senqipicserver.model.entity.User;
import com.senqicloud.senqipicserver.model.request.UserRegisterRequest;
import com.senqicloud.senqipicserver.model.response.UserRegisterResponse;
import com.senqicloud.senqipicserver.service.UserService;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        // 1. 先查看用户是否已经被注册
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,userRegisterRequest.getUsername());

        User user = this.getOne(queryWrapper);

        if (user != null){
            throw new ValidateException("用户已经存在！");
        }

        // 2. 注册用户
        User registerUser = new User();
        BeanUtils.copyProperties(userRegisterRequest,registerUser);

        boolean save = this.save(registerUser);

        if (!save){
            throw new ServerErrorException("注册失败！");
        }

        // 3. 返回
        UserRegisterResponse retUser = new UserRegisterResponse();
        BeanUtils.copyProperties(registerUser,retUser);

        return retUser;
    }
}
