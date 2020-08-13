package cn.laoshengle.core.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author longjuntao
 * @description: RabbitMQ 消息对象入参
 * @date 2020/8/13 15:06
 */
@Data
public class RabbitMqMessage implements Serializable {
    private static final long serialVersionUID = -7511521675581590845L;

    @NotBlank(message = "消息队列名称不能为空")
    private String queueName;

    @NotBlank(message = "消息内容不能为空")
    private String context;
}
