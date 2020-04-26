package cn.laoshengle.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description: 微信Token校验
 * @author: 龙逸
 * @createDate: 2020/04/25 21:31:23
 **/
public class WeChatCheckoutUtil {

    private final static Logger logger = LoggerFactory.getLogger(WeChatCheckoutUtil.class);

    /**
     * 与接口配置信息中的Token要一致
     */
    private final static String TOKEN = "LongJunTao";

    /**
     * 验证签名
     *
     * @param signature 不知道啥参数 X1
     * @param timestamp 不知道啥参数 X2
     * @param nonce     不知道啥参数 X3
     * @return 验证结果
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{TOKEN, timestamp, nonce};
        sort(arr);
        StringBuilder content = new StringBuilder();
        for (String temp : arr) {
            content.append(temp);
        }
        MessageDigest md;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            logger.error("[WeChatCheckoutUtil].[checkSignature] Check Signature Exception :", e);
        }
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        boolean result = tmpStr != null && tmpStr.equals(signature.toUpperCase());
        logger.info("[WeChatCheckoutUtil].[checkSignature] Check Result : {}", result);
        return result;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray 入参
     * @return 转换结果
     */
    private static String byteToStr(byte[] byteArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte temp : byteArray) {
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }

    public static void sort(String[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[i]) < 0) {
                    String temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }


}
