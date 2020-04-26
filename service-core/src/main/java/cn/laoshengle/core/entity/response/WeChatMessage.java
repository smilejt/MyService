package cn.laoshengle.core.entity.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/04/26 11:13:30
 **/
@Data
public class WeChatMessage implements Serializable {
    private static final long serialVersionUID = -3363342511543244659L;

    /**
     * 开发者微信号
     */
    private String ToUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    private String FromUserName;

    /**
     * 消息创建时间(时间戳)
     */
    private Date CreateTime;

    /**
     * 消息类型
     * 文本为text
     * 图片为image
     */
    private String MsgType;

    /**
     * 文本消息内容
     */
    private String Content;

    /**
     * 图片链接
     */
    private String PicUrl;

    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String MediaId;

    /**
     * 消息id，64位整型
     */
    private Long MsgId;
}
