package cn.laoshengle.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author longjuntao
 * @description:
 * @date 2020/8/5 21:13
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    private static final Logger logger = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        logger.info("---------------------GatewayApplication Successful Start---------------------");
    }
}
