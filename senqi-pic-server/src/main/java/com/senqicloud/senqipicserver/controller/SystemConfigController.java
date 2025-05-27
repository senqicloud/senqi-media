package com.senqicloud.senqipicserver.controller;

import com.senqicloud.senqipicserver.enums.SystemConfigEnum;
import com.senqicloud.senqipicserver.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/config")
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    // 获取系统配置信息
    @GetMapping("/all")
    public Map<String, String> getAllConfigs() {
        return systemConfigService.getAllConfigs();
    }

    // 获取指定系统配置
    @GetMapping("/{key}")
    public String getConfigByKey(@PathVariable("key") String key) {

        if (!SystemConfigEnum.containsKey(key)){
            // TODO 抛出自定义异常
            //  throw new Exception("系统配置 KEY 错误！");
        }
        return systemConfigService.getConfigByKey(key);
    }
}
