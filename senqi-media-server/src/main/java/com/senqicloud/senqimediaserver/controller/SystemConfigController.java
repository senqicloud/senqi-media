package com.senqicloud.senqimediaserver.controller;

import com.senqicloud.senqimediaserver.enums.SystemConfigType;
import com.senqicloud.senqimediaserver.exception.ValidateException;
import com.senqicloud.senqimediaserver.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *  系统配置管理
 * */

@RestController
@RequestMapping("/config")
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    /**
     * 获取系统配置信息
     */
    @GetMapping("/getAll")
    public Map<String, String> getAllConfigs() {
        return systemConfigService.getAllConfigs();
    }

    /**
     * 获取指定系统配置
     */
    @GetMapping("/{key}")
    public String getConfigByKey(@PathVariable("key") String key) {
        // 判断参数是否正确
        if (!SystemConfigType.containsKey(key)){
            throw new ValidateException("系统配置 KEY 错误！");
        }

        return systemConfigService.getConfigByKey(key);
    }
}
