package cn.laoshengle.other.impl.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description: 微信消息实体对象(数据库层)
 * @author: 龙逸
 * @createDate: 2020/04/27 22:10:43
 **/
@Data
@ToString
@TableName("wechat_message")
public class WeChatMessagePojo {

    /**
     * 主键ID
     */
    @TableId(value = "message_id")
    private String messageId;

    /**
     * 开发者微信号
     */
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    private String fromUserName;

    /**
     * 消息创建时间(时间戳)
     */
    private Date createTime;

    /**
     * 消息类型
     * 文本为text
     * 图片为image
     */
    private String msgType;

    /**
     * 文本消息内容
     */
    private String content;

    /**
     * 图片链接
     */
    private String picUrl;

    /**
     * 消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private String mediaId;

    /**
     * 语音消息格式
     */
    private String format;

    /**
     * 语音识别结果，UTF8编码
     */
    private String recognition;

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
     */
    private String thumbMediaId;

    /**
     * 地理位置纬度
     */
    private Double locationX;

    /**
     * 地理位置经度
     */
    private Double locationY;

    /**
     * 地图缩放大小
     */
    private Integer scale;

    /**
     * 地理位置信息
     */
    private String label;

    /**
     * 连接消息标题
     */
    private String title;

    /**
     * 链接消息描述
     */
    private String description;

    /**
     * 链接消息Url
     */
    private String url;

    /**
     * 消息id，64位整型
     */
    private Long msgId;

    /**
     * 写入数据库时间
     */
    private Date writeTime;
}
