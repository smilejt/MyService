package cn.laoshengle.wechat.controller;

import cn.laoshengle.core.constant.CommonConstant;
import cn.laoshengle.core.constant.WeChatMsgTypeConstant;
import cn.laoshengle.core.entity.JsonResult;
import cn.laoshengle.core.entity.request.WeChatMessage;
import cn.laoshengle.core.service.wechat.WeChatMessageService;
import cn.laoshengle.core.utils.*;
import cn.laoshengle.wechat.WeChatApplication;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description: 微信公众号相关接口
 * @author: 龙逸
 * @createDate: 2020/04/25 15:19:06
 **/
@RestController
@RequestMapping(value = "weChatService")
public class WeChatController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatApplication.class);

    @Resource
    private WeChatMessageService weChatMessageService;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 测试接口00
     *
     * @param message GET入参
     * @return 结果字符串
     */
    @GetMapping("hi")
    public String hi(@RequestParam("message") String message) {
        logger.info("{} WeChat Controller", message);
        RedisUtil.setObject("testKey", message, TimeUnit.MINUTES, 120L);
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("从Redis读取的数据:{}", RedisUtil.getBucket("testKey"));
        return String.format("%s WeChat Controller", message);
    }

    /**
     * 测试接口01
     *
     * @return 结果字符串
     */
    @RequestMapping(value = "hiNo", method = RequestMethod.GET)
    public String hiNo() {
        String message = "No Message";
        logger.info("{} WeChat Controller", message);
        return String.format("%s WeChat Controller", message);
    }

    /**
     * 微信Token校验接口
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echoStr   随机字符串
     * @return 校验结果
     */
    @GetMapping("receiveWeChatMessage")
    public String tokenVerification(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam("echostr") String echoStr) {

        logger.info("[WeChatController].[receiveWeChatMessage]-----> In : signature = {}, timestamp = {}, nonce = {}, echoStr = {}", signature, timestamp, nonce, echoStr);
        // 通过检验signature对请求进行校验，若校验成功则原样返回echoStr，表示接入成功，否则接入失败
        boolean result = WeChatCheckoutUtil.checkSignature(signature, timestamp, nonce);
        logger.info("result = {}", result);
        if (result) {
            logger.info("[WeChatController].[receiveWeChatMessage]----->Verification True");
            return echoStr;
        }
        return CommonConstant.NULL_STRING;
    }

    /**
     * 微信接受消息
     *
     * @param weChatMessage 消息对象
     * @return 处理结果
     */
    @PostMapping(value = "receiveWeChatMessage", produces = CommonConstant.PRODUCES_STRING)
    public String tokenVerification(@RequestBody WeChatMessage weChatMessage) {
        logger.info("[WeChatController].[receiveWeChatMessage]-----> In : weChatMessage = {}", weChatMessage.toString());

        if (!StringUtils.isEmpty(weChatMessage.getMsgType())) {
            //初始化返回XML(""表示告诉微信不处理)
            String resultXmlString;
            switch (weChatMessage.getMsgType()) {
                case WeChatMsgTypeConstant.TEXT_CODE:
                    //文本消息,异步调用处理
                    ThreadPoolUtil.newTask(() -> {
                        logger.info("[WeChatController].[receiveWeChatMessage]-----> Asynchronous Process Messages");
                        weChatMessageService.handleWeChatTextMessage(weChatMessage);
                        //处理完成,执行客服消息回调逻辑
                    });

                    //当前线程直接返回success
                    resultXmlString = CommonConstant.SUCCESS_STRING;
                    break;
                case WeChatMsgTypeConstant.EVENT_CODE:
                    //关注/取消关注消息
                    resultXmlString = WeChatMessageUtil.defaultEventMessage(weChatMessage);
                    break;
                default:
                    //其他情况
                    resultXmlString = WeChatMessageUtil.defaultMessage(weChatMessage);
            }
            return resultXmlString;
        } else {
            //如果MsgType为空(正常不会出现)
            logger.error("[WeChatController].[receiveWeChatMessage]-----> Error : msgType is null");
            return WeChatMessageUtil.defaultMessage(weChatMessage);
        }
    }

    /**
     * 获取微信AccessToken
     *
     * @return 获取结果
     */
    @GetMapping(value = "getAccessToken")
    public JsonResult getAccessToken() {

        logger.info("[WeChatController].[getAccessToken]------> Request WeChat to get Token, time = {}", DateFormatUtil.parseDateToStr(new Date(), DateFormatUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS));

        String url = String.format("%s%s%s%s%s%s%s%s%s",
                CommonConstant.ACCESS_TOKEN_URL,
                CommonConstant.GET_URL_AND,
                CommonConstant.APP_ID_KEY,
                CommonConstant.EQUAL,
                CommonConstant.APP_ID,
                CommonConstant.GET_URL_AND,
                CommonConstant.APP_SECRET_KEY,
                CommonConstant.EQUAL,
                CommonConstant.APP_SECRET);
        ResponseEntity<String> resultEntity = restTemplate.getForEntity(url, String.class);

        //判断请求是否成功
        if (CommonConstant.HTTP_REQUEST_SUCCESS_CODE != resultEntity.getStatusCodeValue()) {
            logger.error("[WeChatController].[getAccessToken]------> Failed to request WeChat to obtain token");
            return JsonResult.buildFailMsg("请求微信获取Token失败!");
        }

        //将微信返回结果转换成Map
        Map<String, Object> resultMap = JSON.parseObject(resultEntity.getBody());
        if (resultMap.containsKey(CommonConstant.ACCESS_TOKEN_KEY) && !StringUtils.isEmpty(resultMap.get(CommonConstant.ACCESS_TOKEN_KEY))) {
            long time = resultMap.containsKey(CommonConstant.EXPIRES_IN_KEY) ? Long.parseLong(resultMap.get(CommonConstant.EXPIRES_IN_KEY).toString()) : 7200L;

            //写入Redis
            RedisUtil.setObject(CommonConstant.WE_CHAT_TOKEN_KEY, resultMap.get(CommonConstant.ACCESS_TOKEN_KEY), TimeUnit.SECONDS, time);
            logger.info("[WeChatController].[getAccessToken]------> Request WeChat to obtain token successfully");
            return JsonResult.buildSuccessMsg("Request WeChat to obtain token successfully");
        } else {
            int errorCode = resultMap.containsKey(CommonConstant.ERR_CODE_KEY) ? (int) resultMap.get(CommonConstant.ERR_CODE_KEY) : 99999;
            String errorMsg = resultMap.containsKey(CommonConstant.ERR_MSG_KEY) ? (String) resultMap.get(CommonConstant.ERR_MSG_KEY) : null;
            logger.error("[WeChatController].[getAccessToken]------> Failed to request WeChat to obtain token, code = {}, msg = {}, codeDetailed = {}", errorCode, errorMsg, WeChatMessageUtil.weChatTokenErrorCodeDetailed(errorCode));
            return JsonResult.buildFailMsg(WeChatMessageUtil.weChatTokenErrorCodeDetailed(errorCode));
        }
    }
}
