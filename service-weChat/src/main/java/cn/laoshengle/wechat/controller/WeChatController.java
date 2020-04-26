package cn.laoshengle.wechat.controller;

import cn.laoshengle.core.utils.SHA1;
import cn.laoshengle.wechat.WeChatApplication;
import org.aspectj.bridge.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/04/25 15:19:06
 **/
@RestController
@RequestMapping(value = "weChatService")
public class WeChatController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatApplication.class);

    /**
     * 与接口配置信息中的Token要一致
     */

    @GetMapping("hi")
    public String hi(@RequestParam("message") String message) {
        logger.info("{} WeChat Controller", message);
        return String.format("%s WeChat Controller", message);
    }

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
    @GetMapping("tokenVerification")
    public String tokenVerification(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam("echostr") String echoStr) {

        String token = "LongJunTao";
        logger.info("[WeChatController].[tokenVerification]-----> In : signature = {}, timestamp = {}, nonce = {}, echoStr = {}", signature, timestamp, nonce, echoStr);
        // 通过检验signature对请求进行校验，若校验成功则原样返回echoStr，表示接入成功，否则接入失败
        String sha1 = SHA1.getSHA1(token, timestamp, nonce, echoStr);
        logger.info("sha1 = {}", sha1);
        logger.info("[WeChatController].[tokenVerification]----->Verification True");
        return echoStr;
    }

    /**
     * 微信接受消息
     *
     * @param request 消息对象
     * @return 处理结果
     */
//    @PostMapping(value = "tokenVerification", produces = "application/xml")
//    public String tokenVerification(HttpServletRequest request, HttpServletResponse response) throws Exception {
////        logger.info("[WeChatController].[tokenVerification]-----> In : weChatMessage = {}", weChatMessage.toString());
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        Map<String, String> parseXml = MessageUtil.parseXml(request);
//        String msgType = parseXml.get("MsgType");
//        String content = parseXml.get("Content");
//        String fromusername = parseXml.get("FromUserName");
//        String tousername = parseXml.get("ToUserName");
//        System.out.println(msgType);
//        System.out.println(content);
//        System.out.println(fromusername);
//        System.out.println(tousername);
//        return null;
//    }
}
