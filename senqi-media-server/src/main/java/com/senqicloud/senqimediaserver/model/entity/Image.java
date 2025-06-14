package com.senqicloud.senqimediaserver.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.senqicloud.senqimediaserver.enums.StorageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("images")
public class Image {
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 所属用户ID
     */
    private Long userId;

    /**
     * 图片原始文件名
     */
    private String originalName;

    /**
     * 图片唯一标识名（如 UUID 或 hash）
     */
    private String filename;

    /**
     * 图片扩展名，例如 jpg、png
     */
    private String extension;

    /**
     * 图片大小（单位：字节）
     */
    private Long size;

    /**
     * 图片宽度（px）
     */
    private Integer width;

    /**
     * 图片高度（px）
     */
    private Integer height;

    /**
     * MIME类型，如 image/jpeg
     */
    private String mimeType;

    /**
     * 图片URL（或对象存储key）
     */
    private String url;

    /**
     * 图片缩略图URL
     */
    private String thumbnailUrl;

    /**
     * 图片上传时间
     */
    private LocalDateTime uploadTime;

    /**
     * 图片的 SHA256 或 MD5，用于查重
     */
    private String hash;

    /**
     * 图片状态（正常/删除/审核）
     */
    private Integer status;

    /**
     * 存储类型（本地、阿里云、七牛云、S3 等）
     */
    private StorageType storageType;

    /**
     * 存储桶 ID
     */
    private Long storageBucketId;

    /**
     * 是否公开访问
     */
    private Boolean isPublic;

    /**
     * 图片描述（可选）
     */
    private String description;
}
