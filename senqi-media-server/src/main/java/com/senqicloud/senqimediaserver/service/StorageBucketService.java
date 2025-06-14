package com.senqicloud.senqimediaserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senqicloud.senqimediaserver.model.entity.StorageBucket;
import org.springframework.web.multipart.MultipartFile;

public interface StorageBucketService extends IService<StorageBucket> {

    StorageBucket getStorageBucket(MultipartFile file);

    boolean add(StorageBucket storageBucket);

    boolean delete(StorageBucket storageBucket);

    boolean update(StorageBucket storageBucket);
}
