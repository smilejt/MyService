package cn.laoshengle.taobao.impl;

import cn.laoshengle.core.config.HttpsClientRequestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 淘宝数据获取实现服务
 * @author: 龙逸
 * @createDate: 2020/05/04 17:37:50
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TaoBaoImplApplication {

    private static final Logger logger = LoggerFactory.getLogger(TaoBaoImplApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TaoBaoImplApplication.class, args);
        logger.info("---------------------TaoBaoImplApplication Successful Start---------------------");
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