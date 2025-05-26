package com.senqicloud.senqipicserver.controller;

import com.senqicloud.senqipicserver.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system_config")
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    // 获取系统名称
    @GetMapping("/system_name")
    public String getSystemName() {
        return systemConfigService.getSystemName();
    }
}
