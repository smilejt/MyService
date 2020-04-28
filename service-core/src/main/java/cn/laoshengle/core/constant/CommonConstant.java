package cn.laoshengle.core.constant;

/**
 * @description: 通用常量
 * @author: 龙逸
 * @createDate: 2020/04/23 17:29:09
 **/
public class CommonConstant {

    /**
     * 微信接口produces属性
     */
    public static final String PRODUCES_STRING = "application/xml;charset=UTF-8";

    /**
     * 异常时返回给微信的字符串
     */
    public static final String NULL_STRING = "";

    /**
     * 告诉微信成功,暂时不处理
     */
    public static final String SUCCESS_STRING = "success";

    /**
     * ArrayList类路径
     */
    public static final String ARRAY_LIST_STRING = "java.util.ArrayList";

    /**
     * xml开始标签
     */
    public static final String XML_START = "<xml>";

    /**
     * xml结束标签
     */
    public static final String XML_END = "</xml>";

    /**
     * 左尖括号
     */
    public static final String LEFT_BRACKET = "<";

    /**
     * 左尖括号加斜杠
     */
    public static final String LEFT_BRACKET_AND_SLASH = "</";

    /**
     * 右尖括号
     */
    public static final String RIGHT_BRACKET = ">";

    /**
     * CDATA标签左
     */
    public static final String CDATA_LEFT = "><![CDATA[";

    /**
     * CDATA标签右
     */
    public static final String CDATA_RIGHT = "]]></";

    /**
     * 微信接口token
     */
    public static final String WE_CHAT_TOKEN = "LongJunTao";

    /**
     * SHA-1加密
     */
    public static final String SHA_1 = "SHA-1";

    /**
     * defaultMessage
     * 返回暂时不支持的消息类型
     */
    public static final String DEFAULT_MESSAGE_STRING = "抱歉,暂时不支持该消息类型,我们会尽快完善,感谢您的支持!";
    public static final String DEFAULT_EVENT_MESSAGE_STRING = "感谢您对我们的关注,目前我们正处于开发阶段,很多功能尚未完善,开发完成会第一时间通知您!";
}
