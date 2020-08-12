package cn.laoshengle.core.service.other;

import cn.laoshengle.core.constant.FeignConstant;
import cn.laoshengle.core.entity.request.WeChatMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: 微信服务实现接口
 * @author: 龙逸
 * @createDate: 2020/04/27 17:17:35
 **/
@Service
@FeignClient(value = FeignConstant.WE_CHAT_SERVICE_NAME,contextId = "weChatMessageService",path = "api/V1/weChat/weChatMessageService")
public interface WeChatMessageService {

    /**
     * 处理微信文本消息
     * @param weChatMessage 文本消息对象
     * @return 处理结果XML字符串
     */
    @PostMapping("handleWeChatTextMessage")
    String handleWeChatTextMessage(@RequestBody WeChatMessage weChatMessage);
}
