package com.senqicloud.senqimediaserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senqicloud.senqimediaserver.mapper.StorageBucketMapper;
import com.senqicloud.senqimediaserver.model.entity.StorageBucket;
import com.senqicloud.senqimediaserver.service.StorageBucketService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StorageBucketServiceImpl extends ServiceImpl<StorageBucketMapper, StorageBucket> implements StorageBucketService {

    // 存储桶挑选策略
    @Override
    public StorageBucket getStorageBucket(MultipartFile file) {
        // 1. 构建查询器
        LambdaQueryWrapper<StorageBucket> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.nested(w -> w.eq(StorageBucket::getMaxSize, 0) // 容量不限
                        .or().ge(StorageBucket::getAvailableSize, file.getSize()))  // 能存的下
                .eq(StorageBucket::getEnabled, true) // 可用的
                .orderByAsc(StorageBucket::getPriority) // 根据优先级升序排列
                .orderByDesc(StorageBucket::getAvailableSize); // 根据剩余容量排序

        // 2. 获取存储桶列表
        List<StorageBucket> list = this.list(queryWrapper);

        if (list != null && !list.isEmpty()) {
            return list.getFirst();
        }

        return null;
    }

    @Override
    public boolean add(StorageBucket storageBucket) {
        return this.save(storageBucket);
    }

    @Override
    public boolean delete(StorageBucket storageBucket) {
        return this.removeById(storageBucket.getId());
    }

    @Override
    public boolean update(StorageBucket storageBucket) {
        return this.updateById(storageBucket);
    }
}
