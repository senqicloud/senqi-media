package com.senqicloud.senqipicserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senqicloud.senqipicserver.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
