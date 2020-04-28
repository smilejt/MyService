package cn.laoshengle.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 微信模块启动类
 * @author: 龙逸
 * @createDate: 2020/04/25 15:04:16
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"cn.laoshengle.core.service.wechat"})
public class WeChatApplication {

    private static final Logger logger = LoggerFactory.getLogger(WeChatApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WeChatApplication.class, args);
        logger.info("---------------------WeChatApplication Successful Start---------------------");
    }
}
