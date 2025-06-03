package com.senqicloud.senqimediaserver.service.impl;

import com.senqicloud.senqimediaserver.model.entity.StorageBucket;
import com.senqicloud.senqimediaserver.model.response.ImageUploadResponse;
import com.senqicloud.senqimediaserver.service.StorageService;
import com.senqicloud.senqimediaserver.strategy.storage.StorageStrategy;
import com.senqicloud.senqimediaserver.strategy.storage.StorageStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageStrategyFactory storageStrategyFactory;

    @Override
    public ImageUploadResponse upload(MultipartFile file, StorageBucket storageBucket) {
        // 根据存储桶类型获取对应的存储桶策略
        StorageStrategy storageStrategy = storageStrategyFactory.getStrategy(storageBucket.getStorageType());

        // 执行上传文件
        String url = storageStrategy.uploadFile(file, file.getOriginalFilename());

        // 构建返回结构体
        ImageUploadResponse imageUploadResponse = new ImageUploadResponse();
        imageUploadResponse.setUrl(url);

        return imageUploadResponse;
    }
}
