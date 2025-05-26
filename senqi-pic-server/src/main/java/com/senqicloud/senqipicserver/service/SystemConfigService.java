package com.senqicloud.senqipicserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senqicloud.senqipicserver.model.entity.SystemConfig;

public interface SystemConfigService extends IService<SystemConfig> {
    String getSystemName();
}
