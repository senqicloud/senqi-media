package com.senqicloud.senqimediaserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senqicloud.senqimediaserver.enums.SystemConfigType;
import com.senqicloud.senqimediaserver.exception.ValidateException;
import com.senqicloud.senqimediaserver.mapper.SystemConfigMapper;
import com.senqicloud.senqimediaserver.model.entity.SystemConfig;
import com.senqicloud.senqimediaserver.service.SystemConfigService;
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

    @Override
    public Map<String, String> setAllConfigs(Map<String, String> configs) {
        // 遍历每一个键值对
        configs.forEach((key, value) -> {
            // 1. 判断 Key 是否合法
            if (!SystemConfigType.containsKey(key)) {
                throw new ValidateException("包含不合法的配置项！");
            }

            // 2. key 合法，执行更改业务
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.setConfigKey(key);
            systemConfig.setConfigValue(value);

            // 3. 构建更新对象
            LambdaUpdateWrapper<SystemConfig> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(SystemConfig::getConfigKey, key);
            updateWrapper.set(SystemConfig::getConfigValue, systemConfig.getConfigValue());
            this.update(updateWrapper);

        });
        // 4. 返回
        return configs;
    }

    @Override
    public boolean setConfigByKey(SystemConfig systemConfig) {
        return this.saveOrUpdate(systemConfig);
    }
}
