package cn.laoshengle.task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description: 微信相关定时任务
 * @author: 龙逸
 * @createDate: 2020/04/30 20:35:35
 **/
@Component
@EnableScheduling
public class WeChatTask {

    private static final Logger logger = LoggerFactory.getLogger(WeChatTask.class);


    /**
     * 每1小时55分钟获取一次微信AccessToken
     */
    @Scheduled(cron = "0/3 * * * * ?")
    public void getWeChatAccessToken() {
        System.out.println("[WeChatTask].[getWeChatAccessToken]------> start");
        logger.info("[WeChatTask].[getWeChatAccessToken]------> start");
        //通过restTemplate
    }

    //        ResponseEntity<String> resultEntity = restTemplate.getForEntity(CommonConstant.TASK_GET_WE_CHAT_TOKEN_URL, String.class);
//        if (CommonConstant.HTTP_REQUEST_SUCCESS_CODE == resultEntity.getStatusCodeValue()){
//            logger.info("[WeChatTask].[getWeChatAccessToken]------> resultEntity.getBody() = {}",resultEntity.getBody());
//        }else {
//            logger.error("[WeChatTask].[getWeChatAccessToken]------> Failed to obtain token");
//        }
}
