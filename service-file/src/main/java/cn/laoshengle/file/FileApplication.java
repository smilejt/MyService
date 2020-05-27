package cn.laoshengle.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 文件上传服务启动类
 * @author: 龙逸
 * @createDate: 2020/05/24 13:55:10
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class FileApplication {

    private static final Logger logger = LoggerFactory.getLogger(FileApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
        logger.info("---------------------FileApplication Successful Start---------------------");
    }
}
