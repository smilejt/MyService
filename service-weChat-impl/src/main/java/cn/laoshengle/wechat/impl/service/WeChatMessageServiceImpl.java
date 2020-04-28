package cn.laoshengle.wechat.impl.service;

import cn.laoshengle.core.entity.request.WeChatMessage;
import cn.laoshengle.core.enums.WeChatMsgType;
import cn.laoshengle.core.service.wechat.WeChatMessageService;
import cn.laoshengle.core.utils.WeChatMessageUtil;
import cn.laoshengle.wechat.impl.mapper.WeChatMessageMapper;
import cn.laoshengle.wechat.impl.pojo.WeChatMessagePojo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: 微信服务实现类
 * @author: 龙逸
 * @createDate: 2020/04/27 20:48:27
 **/
@Service
@ResponseBody
@RequestMapping("api/V1/weChat/weChatMessageService")
public class WeChatMessageServiceImpl implements WeChatMessageService {

    @Resource
    WeChatMessageMapper weChatMessageMapper;

    @Override
    public String handleWeChatTextMessage(WeChatMessage weChatMessage) {

        WeChatMessagePojo weChatMessagePojo = new WeChatMessagePojo();
        BeanUtils.copyProperties(weChatMessage, weChatMessagePojo);
        //设置UUID
        weChatMessagePojo.setMessageId(UUID.randomUUID().toString().replace("-", ""));
        weChatMessagePojo.setWriteTime(new Date());

        //临时返回
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("ToUserName", weChatMessage.getFromUserName());
        resultMap.put("FromUserName", weChatMessage.getToUserName());
        resultMap.put("CreateTime", new Date());
        resultMap.put("MsgType", WeChatMsgType.text);
        if (weChatMessageMapper.insertSelective(weChatMessagePojo) == 1) {
            //写入数据库成功
            resultMap.put("Content", "您的留言我们已经记录下来了,将会尽快处理!");
        } else {
            //写入数据库失败
            resultMap.put("Content", "抱歉,我们系统出现了异常,将尽快解决!");
        }
        return WeChatMessageUtil.mapToXml(resultMap);
    }
}
