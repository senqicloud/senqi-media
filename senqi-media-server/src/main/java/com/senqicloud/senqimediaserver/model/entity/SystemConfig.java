package com.senqicloud.senqimediaserver.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 系统配置表
@Data
@TableName("system_config")
public class SystemConfig {
    @TableId(type = IdType.AUTO)
    Long id;

    String configKey;

    String configValue;
}
