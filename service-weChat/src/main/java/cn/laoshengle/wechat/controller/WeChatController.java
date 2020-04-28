package cn.laoshengle.wechat.controller;

import cn.laoshengle.core.constant.CommonConstant;
import cn.laoshengle.core.constant.WeChatMsgTypeConstant;
import cn.laoshengle.core.entity.request.WeChatMessage;
import cn.laoshengle.core.service.wechat.WeChatMessageService;
import cn.laoshengle.core.utils.ThreadPoolUtil;
import cn.laoshengle.core.utils.WeChatCheckoutUtil;
import cn.laoshengle.core.utils.WeChatMessageUtil;
import cn.laoshengle.wechat.WeChatApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/04/25 15:19:06
 **/
@RestController
@RequestMapping(value = "weChatService")
public class WeChatController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatApplication.class);

    @Resource
    private WeChatMessageService weChatMessageService;

    /**
     * 测试接口00
     *
     * @param message GET入参
     * @return 结果字符串
     */
    @GetMapping("hi")
    public String hi(@RequestParam("message") String message) {
        logger.info("{} WeChat Controller", message);
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
                    ThreadPoolUtil.newTask(new Runnable() {
                        @Override
                        public void run() {
                            logger.info("[WeChatController].[receiveWeChatMessage]-----> Asynchronous Process Messages");
                            weChatMessageService.handleWeChatTextMessage(weChatMessage);
                        }
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
}
