package cn.laoshengle.task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Scheduled(cron="*/6 * * * * ?")
    private void getWeChatAccessToken(){

    }
}
