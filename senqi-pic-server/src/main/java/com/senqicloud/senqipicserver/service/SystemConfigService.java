package com.senqicloud.senqipicserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senqicloud.senqipicserver.model.entity.SystemConfig;

import java.util.Map;

public interface SystemConfigService extends IService<SystemConfig> {

    String getConfigByKey(String key);

    Map<String, String> getAllConfigs();
}
