package com.senqicloud.senqipicserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senqicloud.senqipicserver.exception.NotFoundException;
import com.senqicloud.senqipicserver.exception.ValidateException;
import com.senqicloud.senqipicserver.mapper.SystemConfigMapper;
import com.senqicloud.senqipicserver.model.entity.SystemConfig;
import com.senqicloud.senqipicserver.service.SystemConfigService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {

    @Override
    public String getConfigByKey(String key) {
        // 1. 构建查询构造器
        LambdaQueryWrapper<SystemConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SystemConfig::getConfigKey, key);

        // 2. 查询
        SystemConfig systemConfig = this.getOne(queryWrapper);

        // 3. 查询结果为空
        if (systemConfig == null) {
            throw new ValidateException("系统配置 KEY 错误！");
        }
        return systemConfig.getConfigValue();
    }

    @Override
    public Map<String, String> getAllConfigs() {
        // 获取所有配置信息
        List<SystemConfig> list = this.list();

        // 封装配置信息结构
        return list.stream()
                .collect(Collectors.toMap(SystemConfig::getConfigKey, SystemConfig::getConfigValue));
    }
}
