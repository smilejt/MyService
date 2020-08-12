package cn.laoshengle.other.impl.service;

import cn.laoshengle.core.entity.request.WeChatMessage;
import cn.laoshengle.core.service.other.WeChatMessageService;
import cn.laoshengle.core.utils.BaseUtil;
import cn.laoshengle.other.impl.mapper.WeChatMessageMapper;
import cn.laoshengle.other.impl.pojo.WeChatMessagePojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description: 微信服务实现类
 * @author: 龙逸
 * @createDate: 2020/04/27 20:48:27
 **/
@Service
@ResponseBody
@RequestMapping("api/V1/weChat/weChatMessageService")
public class WeChatMessageServiceImpl implements WeChatMessageService {

    private static final Logger logger = LoggerFactory.getLogger(WeChatMessageServiceImpl.class);

    @Resource
    private WeChatMessageMapper weChatMessageMapper;

    private static final Long ONE_THOUSAND = 1000L;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String handleWeChatTextMessage(WeChatMessage weChatMessage) {

        WeChatMessagePojo weChatMessagePojo = new WeChatMessagePojo();
        BeanUtils.copyProperties(weChatMessage, weChatMessagePojo);
        //设置UUID
        weChatMessagePojo.setMessageId(BaseUtil.getUUID());
        weChatMessagePojo.setWriteTime(new Date());

        weChatMessagePojo.setCreateTime(new Date(weChatMessage.getMsgCreateTime() * ONE_THOUSAND));

        String resultString;

        if (weChatMessageMapper.insert(weChatMessagePojo) == 1) {
            //写入数据库成功
            resultString = "您的留言我们已经记录下来了,将会尽快处理!";
        } else {
            //写入数据库失败
            resultString = "抱歉,我们系统出现了异常,将尽快解决!";
        }
        return resultString;
    }
}
