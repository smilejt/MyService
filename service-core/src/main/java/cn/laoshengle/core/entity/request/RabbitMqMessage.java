package cn.laoshengle.core.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author longjuntao
 * @description: RabbitMQ 消息对象入参
 * @date 2020/8/13 15:06
 */
@Data
public class RabbitMqMessage implements Serializable {
    private static final long serialVersionUID = -7511521675581590845L;

    private String queueName;

    private String context;
}
