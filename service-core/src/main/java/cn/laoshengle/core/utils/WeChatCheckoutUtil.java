package cn.laoshengle.core.utils;

import cn.laoshengle.core.constant.CommonConstant;
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
     * @param signature 微信接口加密后的字符串
     * @param timestamp 微信接口时间戳
     * @param nonce     微信接口随机字符串
     * @return 验证结果
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{CommonConstant.WE_CHAT_TOKEN, timestamp, nonce};
        //将token、timestamp、nonce三个参数进行字典序排序
        sort(arr);
        StringBuilder content = new StringBuilder();
        for (String s : arr) {
            content.append(s);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance(CommonConstant.SHA_1);
            //将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            logger.error("[WeChatCheckoutUtil].[checkSignature]------> Error :", e);
            e.printStackTrace();
        }

        content = null;
        //将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null && tmpStr.equals(signature.toUpperCase());
    }

    /**
     *    * 将字节数组转换为十六进制字符串
     *    *
     *    * @param byteArray
     *    * @return
     *    
     */
    private static String byteToStr(byte[] byteArray) {
        StringBuilder strDigest = new StringBuilder(CommonConstant.NULL_STRING);
        for (byte b : byteArray) {
            strDigest.append(byteToHexStr(b));
        }
        return strDigest.toString();
    }

    /**
     *    * 将字节转换为十六进制字符串
     *    *
     *    * @param mByte
     *    * @return
     *    
     */
    private static String byteToHexStr(byte mByte) {
        char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];

        return new String(tempArr);
    }

    private static void sort(String[] a) {
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
