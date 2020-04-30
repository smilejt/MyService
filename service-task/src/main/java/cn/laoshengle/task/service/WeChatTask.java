package cn.laoshengle.task.service;

import cn.laoshengle.core.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description: 微信相关定时任务
 * @author: 龙逸
 * @createDate: 2020/04/30 20:35:35
 **/
@Component
public class WeChatTask {

    @Resource
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(WeChatTask.class);

    /**
     * 每1小时55分钟获取一次微信AccessToken
     */
    @Scheduled(fixedRate = 6900000)
    private void getWeChatAccessToken() {
        //通过restTemplate
        ResponseEntity<String> resultEntity = restTemplate.getForEntity(CommonConstant.TASK_GET_WE_CHAT_TOKEN_URL, String.class);
    }
}
