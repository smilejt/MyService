package cn.laoshengle.other;

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
 * @description: 微信模块启动类
 * @author: 龙逸
 * @createDate: 2020/04/25 15:04:16
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"cn.laoshengle.core.service.other"})
public class OtherApplication {

    private static final Logger logger = LoggerFactory.getLogger(OtherApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class, args);
        logger.info("---------------------OtherApplication Successful Start---------------------");
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
