package com.senqicloud.senqimediaserver.handler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senqicloud.senqimediaserver.model.entity.StorageBucketConfig;

// 存储配置类型处理器
public class StorageConfigTypeHandler extends AbstractJsonTypeHandler<StorageBucketConfig> {

    public StorageConfigTypeHandler(Class<?> type) {
        super(type);
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public StorageBucketConfig parse(String json) {
        try {
            return objectMapper.readValue(json, StorageBucketConfig.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON反序列化失败", e);
        }
    }

    @Override
    public String toJson(StorageBucketConfig obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("JSON序列化失败", e);
        }
    }
}