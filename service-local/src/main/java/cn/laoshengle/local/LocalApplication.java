package cn.laoshengle.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 本地服务(极度耗费资源, 又仅内部使用)
 * @author: 龙逸
 * @createDate: 2020/05/07 19:52:22
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class LocalApplication {

    private static final Logger logger = LoggerFactory.getLogger(LocalApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LocalApplication.class, args);
        logger.info("---------------------LocalApplication Successful Start---------------------");
    }
}
