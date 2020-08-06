package cn.laoshengle.task;

import cn.laoshengle.core.config.HttpsClientRequestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 定时任务启动类
 * @author: 龙逸
 * @createDate: 2020/04/30 18:53:35
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class TaskApplication {

    private static final Logger logger = LoggerFactory.getLogger(TaskApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
        logger.info("---------------------TaskApplication Successful Start---------------------");
    }

    /**
     * 将RestTemplate注册成Bean
     *
     * @return new一个RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpsClientRequestFactory());
    }
}
