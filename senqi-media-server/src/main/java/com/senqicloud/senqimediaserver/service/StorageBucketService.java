package com.senqicloud.senqimediaserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senqicloud.senqimediaserver.model.entity.StorageBucket;

public interface StorageBucketService extends IService<StorageBucket> {

    StorageBucket getStorageBucket();

}
