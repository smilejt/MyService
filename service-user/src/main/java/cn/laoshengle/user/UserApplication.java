package cn.laoshengle.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/08/18 18:04:27
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"cn.laoshengle.core.service.user"})
public class UserApplication {

    private static final Logger logger = LoggerFactory.getLogger(UserApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        logger.info("---------------------UserApplication Successful Start---------------------");
    }

}
