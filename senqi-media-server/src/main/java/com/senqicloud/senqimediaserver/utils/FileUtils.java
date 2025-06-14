package com.senqicloud.senqimediaserver.utils;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/** 文件相关的工具类 */
public class FileUtils {

    // 图片格式扩展名
    private static final List<String> IMAGE_EXTENSIONS =
            Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", "webp");

    // 视频格式扩展名
    private static final List<String> VIDEO_EXTENSIONS =
            Arrays.asList("mp4", "avi", "mov", "mkv", "flv", "wmv");

    /**
     * 判断文件是否为图片
     *
     * @param file 文件
     * @return true 如果是图片文件，false 如果不是
     */
    public static boolean isImage(MultipartFile file) {
        if (file == null) {
            return false;
        }

        // 通过扩展名判断
        String fileName = file.getOriginalFilename();
        if (fileName != null
                && IMAGE_EXTENSIONS.stream().anyMatch(ext -> fileName.toLowerCase().endsWith("." + ext))) {
            return true;
        }

        // 通过 MIME 类型判断
        String contentType = file.getContentType();
        if (contentType != null && contentType.startsWith("image/")) {
            return true;
        }

        return false;
    }

    /**
     * 判断文件是否为视频
     *
     * @param file 文件
     * @return true 如果是视频文件，false 如果不是
     */
    public static boolean isVideo(MultipartFile file) {
        if (file == null) {
            return false;
        }

        // 通过扩展名判断
        String fileName = file.getOriginalFilename();
        if (fileName != null
                && VIDEO_EXTENSIONS.stream().anyMatch(ext -> fileName.toLowerCase().endsWith("." + ext))) {
            return true;
        }

        // 通过 MIME 类型判断
        String contentType = file.getContentType();
        if (contentType != null && contentType.startsWith("video/")) {
            return true;
        }

        return false;
    }
}
