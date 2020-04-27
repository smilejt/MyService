package cn.laoshengle.core.entity.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/04/26 11:13:30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "xml")
public class WeChatMessage implements Serializable {
    private static final long serialVersionUID = -3363342511543244659L;

    /**
     * 开发者微信号
     */
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间(时间戳)
     */
    @JacksonXmlProperty(localName = "CreateTime")
    private Date createTime;

    /**
     * 消息类型
     * 文本为text
     * 图片为image
     */
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;

    /**
     * 文本消息内容
     */
    @JacksonXmlProperty(localName = "Content")
    private String content;

    /**
     * 图片链接
     */
    @JacksonXmlProperty(localName = "PicUrl")
    private String picUrl;

    /**
     * 消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    @JacksonXmlProperty(localName = "MediaId")
    private String mediaId;

    /**
     * 语音消息格式
     */
    @JacksonXmlProperty(localName = "Format")
    private String format;

    /**
     * 语音识别结果，UTF8编码
     */
    @JacksonXmlProperty(localName = "Recognition")
    private String recognition;

    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
     */
    @JacksonXmlProperty(localName = "ThumbMediaId")
    private String thumbMediaId;

    /**
     * 地理位置维度
     */
    @JacksonXmlProperty(localName = "Location_X")
    private Double locationX;

    /**
     * 地理位置经度
     */
    @JacksonXmlProperty(localName = "Location_Y")
    private Double locationY;

    /**
     * 地图缩放大小
     */
    @JacksonXmlProperty(localName = "Scale")
    private Integer scale;

    /**
     * 地理位置信息
     */
    @JacksonXmlProperty(localName = "Label")
    private String label;

    /**
     * 连接消息标题
     */
    @JacksonXmlProperty(localName = "Title")
    private String title;

    /**
     * 链接消息描述
     */
    @JacksonXmlProperty(localName = "Description")
    private String description;

    /**
     * 链接消息Url
     */
    @JacksonXmlProperty(localName = "Url")
    private String url;

    /**
     * 消息id，64位整型
     */
    @JacksonXmlProperty(localName = "MsgId")
    private Long msgId;
}
