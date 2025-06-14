package com.senqicloud.senqimediaserver.controller;

import com.senqicloud.senqimediaserver.enums.SystemConfigType;
import com.senqicloud.senqimediaserver.exception.ServerErrorException;
import com.senqicloud.senqimediaserver.exception.ValidateException;
import com.senqicloud.senqimediaserver.model.entity.SystemConfig;
import com.senqicloud.senqimediaserver.service.SystemConfigService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** 系统配置管理 */
@RestController
@RequestMapping("/config")
public class SystemConfigController {

    @Autowired private SystemConfigService systemConfigService;

    /** 获取系统配置信息 */
    @GetMapping("/getAll")
    public Map<String, String> getAllConfigs() {
        System.out.println("123");
        return systemConfigService.getAllConfigs();
    }

    /** 设置系统配置信息 */
    @PutMapping("/setAll")
    public Map<String, String> setAllConfigs(@RequestBody Map<String, String> configs) {
        return systemConfigService.setAllConfigs(configs);
    }

    /** 获取指定系统配置 */
    @GetMapping("/{key}")
    public String getConfigByKey(@PathVariable("key") String key) {
        // 判断参数是否正确
        if (!SystemConfigType.containsKey(key)) {
            throw new ValidateException("系统配置 KEY 错误！");
        }

        return systemConfigService.getConfigByKey(key);
    }

    /** 设置指定系统配置 */
    @PutMapping
    public String setConfigByKey(@RequestBody SystemConfig systemConfig) {
        // 判断参数是否正确
        if (!SystemConfigType.containsKey(systemConfig.getConfigKey())) {
            throw new ValidateException("系统配置 KEY 错误！");
        }

        boolean isSet = systemConfigService.setConfigByKey(systemConfig);

        if (isSet) {
            return "设置成功！";
        } else {
            throw new ServerErrorException("设置失败！");
        }
    }
}
