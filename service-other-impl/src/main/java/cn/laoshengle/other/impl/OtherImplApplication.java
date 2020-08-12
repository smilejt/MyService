package cn.laoshengle.other.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 微信服务实现类
 * @author: 龙逸
 * @createDate: 2020/04/27 17:45:33
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OtherImplApplication {

    private static final Logger logger = LoggerFactory.getLogger(OtherImplApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OtherImplApplication.class, args);
        logger.info("---------------------WeChatImplApplication Successful Start---------------------");
    }
}
