package cn.laoshengle.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author longjuntao
 * @description:
 * @date 2020/8/10 14:44
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UserImplApplication {

    private static final Logger logger = LoggerFactory.getLogger(UserImplApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UserImplApplication.class, args);
        logger.info("---------------------UserImplApplication Successful Start---------------------");
    }

}
