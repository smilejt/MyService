package cn.laoshengle.core.utils;

import cn.laoshengle.core.constant.CommonConstant;

import java.util.UUID;

/**
 * @author longjuntao
 * @description: 基本帮助类
 * @date 2020/8/6 14:50
 */
public class BaseUtil {
    /**
     * 获取UUID
     *
     * @return 去除减号的UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace(CommonConstant.SYMBOL_MINUS, CommonConstant.NULL_STRING);
    }
}
