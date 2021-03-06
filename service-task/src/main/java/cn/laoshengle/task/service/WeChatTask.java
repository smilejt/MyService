package cn.laoshengle.task.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
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
@EnableScheduling
public class WeChatTask {

    private static final Logger logger = LoggerFactory.getLogger(WeChatTask.class);

    @Resource
    private RestTemplate restTemplate;

    private static final String URL = "https://laoshengle.cn/api/other/weChatService/getAccessToken";

    /**
     * 每1小时获取一次微信AccessToken
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void getWeChatAccessToken() {
        logger.info("[WeChatTask].[getWeChatAccessToken]------> start");
        //通过restTemplate
        ResponseEntity<String> weChatTokenResult = restTemplate.getForEntity(URL, String.class);
        logger.info("[WeChatTask].[getWeChatAccessToken]------> end, weChatTokenResult = {}", JSON.toJSONString(weChatTokenResult));
    }

}
