package cn.laoshengle.message.controller;

import cn.laoshengle.core.constant.CommonConstant;
import cn.laoshengle.core.entity.JsonResult;
import cn.laoshengle.core.entity.MiNiProjectBannerEntity;
import cn.laoshengle.core.entity.request.RabbitMqMessage;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author longjuntao
 * @description:
 * @date 2020/8/13 14:12
 */
@RestController
@RequestMapping(value = "rabbitMqController")
public class RabbitMqController {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqController.class);

    private static final String EX_CHANGE_MSG_ERROR = "exChangeMsgError";

    private static final String ROUTING_KEY_ERROR = "routingKeyError";

    @Resource
    private RabbitAdmin rabbitAdmin;

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(rabbitTemplate, EX_CHANGE_MSG_ERROR, ROUTING_KEY_ERROR);
    }

    @PostMapping("sendMq")
    public JsonResult sendMq(@RequestBody RabbitMqMessage message) {
        logger.info("[RabbitMqController].[sendMq]------> Send RabbitMQ Message Start, message = {}", JSON.toJSONString(message));
        this.sendMessage(message.getQueueName(), message.getContext());
        return JsonResult.buildSuccess();
    }

    @PostMapping("sendMqJsonTest")
    public JsonResult sendMqJsonTest(@RequestBody MiNiProjectBannerEntity entity) {
        logger.info("[RabbitMqController].[sendMqJsonTest]------> Send RabbitMQ Message Start, entity = {}", JSON.toJSONString(entity));
        this.sendMessage(CommonConstant.QUEUE_NAME, JSON.toJSONString(entity));
        return JsonResult.buildSuccess();
    }

    private void sendMessage(String queueName, String message) {
        this.declareBinding(queueName, queueName);
        rabbitAdmin.getRabbitTemplate().convertAndSend(queueName, queueName, message);
    }

    private void declareBinding(String exChangeName, String queueName) {
        if (StringUtils.isEmpty(rabbitAdmin.getQueueProperties(queueName))) {
            Queue queue = new Queue(queueName, true, false, false, null);
            rabbitAdmin.declareQueue(queue);
            TopicExchange directExChange = new TopicExchange(exChangeName);
            rabbitAdmin.declareExchange(directExChange);
            Binding binding = BindingBuilder.bind(queue).to(directExChange).with(queueName);
            rabbitAdmin.declareBinding(binding);
        }
    }
}
