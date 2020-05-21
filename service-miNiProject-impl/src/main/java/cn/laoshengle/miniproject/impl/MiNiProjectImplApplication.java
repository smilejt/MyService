package cn.laoshengle.miniproject.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/21 19:43:52
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MiNiProjectImplApplication {
    private static final Logger logger = LoggerFactory.getLogger(MiNiProjectImplApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MiNiProjectImplApplication.class, args);
        logger.info("---------------------MiNiProjectImplApplication Successful Start---------------------");
    }
}
