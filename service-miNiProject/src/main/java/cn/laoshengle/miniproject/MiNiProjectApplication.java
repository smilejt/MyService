package cn.laoshengle.miniproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/21 19:16:28
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"cn.laoshengle.core.service.miniproject"})
public class MiNiProjectApplication {
    private static final Logger logger = LoggerFactory.getLogger(MiNiProjectApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MiNiProjectApplication.class, args);

        logger.info("---------------------MiNiProjectApplication Successful Start---------------------");
    }
}
