package com.senqicloud.senqipicserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senqicloud.senqipicserver.enums.SystemConfigEnum;
import com.senqicloud.senqipicserver.mapper.SystemConfigMapper;
import com.senqicloud.senqipicserver.model.entity.SystemConfig;
import com.senqicloud.senqipicserver.service.SystemConfigService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
            // TODO 这里需要更新为自定义异常
            return "数据不存在！";
        }
        return systemConfig.getConfigValue();
    }

    @Override
    public Map<String, String> getAllConfigs() {
        List<Map<String, Object>> maps = this.listMaps();


        return Map.of();
    }
}
