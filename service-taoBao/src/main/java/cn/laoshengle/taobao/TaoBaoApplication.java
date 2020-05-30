package cn.laoshengle.taobao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/07 14:57:46
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients({"cn.laoshengle.core.service.*"})
public class TaoBaoApplication {

    private static final Logger logger = LoggerFactory.getLogger(TaoBaoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TaoBaoApplication.class, args);

        logger.info("---------------------TaoBaoApplication Successful Start---------------------");
    }
}
