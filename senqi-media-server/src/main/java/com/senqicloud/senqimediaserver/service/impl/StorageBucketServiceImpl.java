package com.senqicloud.senqimediaserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senqicloud.senqimediaserver.mapper.StorageBucketMapper;
import com.senqicloud.senqimediaserver.model.entity.StorageBucket;
import com.senqicloud.senqimediaserver.service.StorageBucketService;
import org.springframework.stereotype.Service;

@Service
public class StorageBucketServiceImpl extends ServiceImpl<StorageBucketMapper, StorageBucket> implements StorageBucketService {

    @Override
    public StorageBucket getStorageBucket() {
        // TODO 存储桶挑选策略
        return null;
    }
}
