package cn.laoshengle.message.mq;

import cn.laoshengle.core.constant.CommonConstant;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author longjuntao
 * @description: RabbitMQ 消息队列消费者
 * @date 2020/8/13 15:14
 */
@Component
@RabbitListener(queues = CommonConstant.QUEUE_NAME)
public class RabbitMqReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @RabbitHandler
    public void process(String temp) {
        logger.info("[RabbitMqReceiver].[process] temp = {}", temp);
    }
}
