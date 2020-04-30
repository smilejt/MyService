package cn.laoshengle.core.utils;

import cn.laoshengle.core.constant.CommonConstant;
import cn.laoshengle.core.entity.JsonResult;
import cn.laoshengle.core.entity.request.WeChatMessage;
import cn.laoshengle.core.enums.WeChatMsgType;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
     *
     * @param weChatMessage 微信消息入参对象
     * @return XML字符串
     */
    public static String defaultMessage(WeChatMessage weChatMessage) {
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
     *
     * @param weChatMessage 默认微信关注回复
     * @return XML字符串
     */
    public static String defaultEventMessage(WeChatMessage weChatMessage) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put(MAP_KEY_TO_USER, weChatMessage.getFromUserName());
        resultMap.put(MAP_KEY_FROM_USER, weChatMessage.getToUserName());
        resultMap.put(MAP_KEY_CREATE_TIME, new Date());
        resultMap.put(MAP_KEY_MSG_TYPE, WeChatMsgType.text);
        resultMap.put(MAP_KEY_CONTENT, CommonConstant.DEFAULT_EVENT_MESSAGE_STRING);
        return mapToXml(resultMap);
    }

    /**
     * 微信Token获取错误码对应信息
     *
     * @param errorCode 错误码
     * @return 对应的错误原因
     */
    public static String weChatTokenErrorCodeDetailed(Integer errorCode) {
        if (errorCode != null) {
            switch (errorCode) {
                case -1:
                    return CommonConstant.WE_CHAT_ERROR_CODE_MINUS_ONE;
                case 0:
                    return CommonConstant.WE_CHAT_ERROR_CODE_0;
                case 40001:
                    return CommonConstant.WE_CHAT_ERROR_CODE_40001;
                case 40002:
                    return CommonConstant.WE_CHAT_ERROR_CODE_40002;
                case 40164:
                    return CommonConstant.WE_CHAT_ERROR_CODE_40164;
                case 89503:
                    return CommonConstant.WE_CHAT_ERROR_CODE_89503;
                case 89501:
                    return CommonConstant.WE_CHAT_ERROR_CODE_89501;
                case 89506:
                    return CommonConstant.WE_CHAT_ERROR_CODE_89506;
                case 89507:
                    return CommonConstant.WE_CHAT_ERROR_CODE_89507;
                default:
                    return CommonConstant.WE_CHAT_ERROR_CODE_OTHER;
            }
        }
        return null;
    }

    /**
     * 前期测试用,异步回复消息
     *
     * @param messageText 消息内容
     * @param toUser      接收的用户名
     */
    public static void sendWeChatMessageToUserName(RestTemplate restTemplate, String messageText, String toUser) {

        Map<String, Object> contentMap = new HashMap<>();
        contentMap.put("content", messageText);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("touser", toUser);
        resultMap.put("msgtype", "text");
        resultMap.put("text", contentMap);

        //组装Headers及请求体
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<>(JSON.toJSONString(resultMap), headers);

        //组装请求URL
        String url = String.format("%s%s%s%s", CommonConstant.REPLY_WE_CHAT_MESSAGE_URL, CommonConstant.ACCESS_TOKEN_KEY, CommonConstant.EQUAL, RedisUtil.getBucket(CommonConstant.WE_CHAT_TOKEN_KEY).get());
        ResponseEntity<String> resultEntity = restTemplate.postForEntity(url, formEntity, String.class);
        if (CommonConstant.HTTP_REQUEST_SUCCESS_CODE != resultEntity.getStatusCodeValue()) {
            logger.error("[WeChatMessageUtil].[sendWeChatMessageToUserName]------> Failed to reply to WeChat message");
        } else {
            logger.info("[WeChatMessageUtil].[sendWeChatMessageToUserName]------> resultEntity.getBody() = {}", resultEntity.getBody());
        }
    }
}
