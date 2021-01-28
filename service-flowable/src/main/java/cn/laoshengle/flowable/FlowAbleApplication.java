package cn.laoshengle.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/06/16 20:49:05
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class FlowAbleApplication {

    private static final Logger logger = LoggerFactory.getLogger(FlowAbleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FlowAbleApplication.class, args);
        logger.info("---------------------LocalApplication Successful Start---------------------");
    }

    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService) {

        return strings -> {
            logger.info("[FlowAbleApplication].[init]------> Number of process definitions : {}", repositoryService.createProcessDefinitionQuery().count());
            logger.info("[FlowAbleApplication].[init]------> Number of tasks : {}", taskService.createTaskQuery().count());
            runtimeService.startProcessInstanceByKey("oneTaskProcess");
            logger.info("[FlowAbleApplication].[init]------> Number of tasks after process start: {}", taskService.createTaskQuery().count());
        };
    }
}
