package com.senqicloud.senqimediaserver.controller;

import com.senqicloud.senqimediaserver.exception.ValidateException;
import com.senqicloud.senqimediaserver.model.entity.StorageBucket;
import com.senqicloud.senqimediaserver.service.StorageBucketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** 存储桶管理 */
@RestController
@RequestMapping("/storage_bucket")
public class StorageBucketController {

    @Autowired private StorageBucketService storageBucketService;

    /** 添加存储桶 */
    @PostMapping
    public boolean add(StorageBucket storageBucket) {
        if (storageBucket == null) {
            throw new ValidateException("存储桶配置异常！");
        }

        return storageBucketService.add(storageBucket);
    }

    /** 删除存储桶 */
    @DeleteMapping
    public boolean delete(StorageBucket storageBucket) {
        if (storageBucket == null) {
            throw new ValidateException("存储桶配置异常！");
        }

        return storageBucketService.delete(storageBucket);
    }

    /** 修改存储桶 */
    @PutMapping
    public boolean update(StorageBucket storageBucket) {
        if (storageBucket == null) {
            throw new ValidateException("存储桶配置异常！");
        }

        return storageBucketService.update(storageBucket);
    }

    /** 查询指定存储桶 */
    @GetMapping("/{id}")
    public StorageBucket get(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            throw new ValidateException("存储桶 ID 错误！");
        }
        return storageBucketService.getById(id);
    }

    /** 查询存储桶列表 */
    @GetMapping("/list")
    public List<StorageBucket> list() {

        return storageBucketService.list();
    }
}
