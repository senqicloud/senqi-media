package com.senqicloud.senqimediaserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senqicloud.senqimediaserver.model.entity.SystemConfig;

import java.util.Map;

public interface SystemConfigService extends IService<SystemConfig> {

    String getConfigByKey(String key);

    Map<String, String> getAllConfigs();

    Map<String, String> setAllConfigs(Map<String, String> configs);

    boolean setConfigByKey(SystemConfig config);
}
