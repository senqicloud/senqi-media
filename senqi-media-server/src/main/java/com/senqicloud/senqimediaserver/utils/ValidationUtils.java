package com.senqicloud.senqimediaserver.utils;

// 格式校验工具类
public class ValidationUtils {

    // 私有构造方法，防止实例化
    private ValidationUtils(){

    }

    // 判断输入是否为手机号
    public static boolean isPhone(String input) {
        return input.matches("^1[3-9]\\d{9}$"); // 简单的中国大陆手机号正则
    }

    // 判断输入是否为邮箱
    public static boolean isEmail(String input) {
        return input.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    }
}
