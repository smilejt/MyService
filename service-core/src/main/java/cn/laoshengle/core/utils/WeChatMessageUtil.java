package cn.laoshengle.core.utils;

import cn.laoshengle.core.constant.CommonConstant;
import cn.laoshengle.core.entity.request.WeChatMessage;
import cn.laoshengle.core.enums.WeChatMsgType;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @description: 微信消息处理帮助类
 * @author: 龙逸
 * @createDate: 2020/04/27 10:49:36
 **/
public class WeChatMessageUtil {

    private static final String MAP_KEY_TO_USER = "ToUserName";
    private static final String MAP_KEY_FROM_USER = "FromUserName";
    private static final String MAP_KEY_CREATE_TIME = "CreateTime";
    private static final String MAP_KEY_MSG_TYPE = "MsgType";
    private static final String MAP_KEY_CONTENT = "Content";

    private static final Logger logger = LoggerFactory.getLogger(WeChatMessageUtil.class);

    /**
     * Map转Xml(用于返回微信消息)
     *
     * @param map 需要准换的Map对象
     * @return 转换后的Xml字符串
     */
    public static String mapToXml(Map<String, Object> map) {

        logger.info("[WeChatMessageUtil].[WeChatMessageUtil]------> In map = {}", JSON.toJSONString(map));

        StringBuffer sb = new StringBuffer();
        sb.append(CommonConstant.XML_START);
        mapToXmlDetailed(map, sb);
        sb.append(CommonConstant.XML_END);
        try {
            return sb.toString();
        } catch (Exception e) {
            logger.error("[WeChatMessageUtil].[WeChatMessageUtil]------> Error :", e);
            return CommonConstant.NULL_STRING;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void mapToXmlDetailed(Map<String, Object> map, StringBuffer sb) {
        Set<String> set = map.keySet();
        for (String key : set) {
            Object value = map.get(key);
            if (null == value) {
                value = CommonConstant.NULL_STRING;
            }
            if (CommonConstant.ARRAY_LIST_STRING.equals(value.getClass().getName())) {
                ArrayList<Object> list = (ArrayList<Object>) map.get(key);
                sb.append(CommonConstant.LEFT_BRACKET).append(key).append(CommonConstant.RIGHT_BRACKET);
                for (Object o : list) {
                    Map hm = (HashMap) o;
                    mapToXmlDetailed(hm, sb);
                }
                sb.append(CommonConstant.LEFT_BRACKET_AND_SLASH).append(key).append(CommonConstant.RIGHT_BRACKET);

            } else {
                if (value instanceof HashMap) {
                    sb.append(CommonConstant.LEFT_BRACKET).append(key).append(CommonConstant.RIGHT_BRACKET);
                    mapToXmlDetailed((HashMap) value, sb);
                    sb.append(CommonConstant.LEFT_BRACKET_AND_SLASH).append(key).append(CommonConstant.RIGHT_BRACKET);
                } else {
                    sb.append(CommonConstant.LEFT_BRACKET).append(key).append(CommonConstant.CDATA_LEFT).append(value).append(CommonConstant.CDATA_RIGHT).append(key).append(CommonConstant.RIGHT_BRACKET);
                }

            }

        }
    }

    /**
     * 不支持的消息默认返回
     * @param weChatMessage 微信消息入参对象
     * @return XML字符串
     */
    public static String defaultMessage(WeChatMessage weChatMessage){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(MAP_KEY_TO_USER, weChatMessage.getFromUserName());
        resultMap.put(MAP_KEY_FROM_USER, weChatMessage.getToUserName());
        resultMap.put(MAP_KEY_CREATE_TIME, new Date());
        resultMap.put(MAP_KEY_MSG_TYPE, WeChatMsgType.text);
        resultMap.put(MAP_KEY_CONTENT, CommonConstant.DEFAULT_MESSAGE_STRING);
        return mapToXml(resultMap);
    }

    /**
     * 默认关注回复
     * @param weChatMessage 默认微信关注回复
     * @return XML字符串
     */
    public static String defaultEventMessage(WeChatMessage weChatMessage){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(MAP_KEY_TO_USER, weChatMessage.getFromUserName());
        resultMap.put(MAP_KEY_FROM_USER, weChatMessage.getToUserName());
        resultMap.put(MAP_KEY_CREATE_TIME, new Date());
        resultMap.put(MAP_KEY_MSG_TYPE, WeChatMsgType.text);
        resultMap.put(MAP_KEY_CONTENT, CommonConstant.DEFAULT_EVENT_MESSAGE_STRING);
        return mapToXml(resultMap);
    }
}
