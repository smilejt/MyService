package cn.laoshengle.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author longjuntao
 * @description:
 * @date 2020/8/6 20:30
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthApplication {

    private static final Logger logger = LoggerFactory.getLogger(AuthApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
        logger.info("---------------------AuthApplication Successful Start---------------------");
    }
}
