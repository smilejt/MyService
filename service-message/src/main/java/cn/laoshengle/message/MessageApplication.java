package cn.laoshengle.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author longjuntao
 * @description: 消息服务启动类
 * @date 2020/8/13 13:56
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MessageApplication {

    private static final Logger logger = LoggerFactory.getLogger(MessageApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class);
        logger.info("---------------------MessageApplication Successful Start---------------------");
    }

}
