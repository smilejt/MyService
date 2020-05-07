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

    /**
     * 微信的AppID
     */
    public static final String APP_ID = "wxba0bd24757980434";

    /**
     * 微信的AppSecret密码
     */
    public static final String APP_SECRET = "bf1ff4a393fc240e5256f446ded468af";

    /**
     * 获取微信 AccessToken 的url地址
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    /**
     * 微信客服接口-发消息
     */
    public static final String REPLY_WE_CHAT_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?";

    /**
     * 获取微信Token返回的 AccessToken 的Key
     */
    public static final String ACCESS_TOKEN_KEY = "access_token";

    /**
     * 获取微信Token返回的 Token 有效时间Key
     */
    public static final String EXPIRES_IN_KEY = "expires_in";

    /**
     * 获取微信Token返回的错误码Key
     */
    public static final String ERR_CODE_KEY = "errcode";

    /**
     * 获取微信Token返回的错误信息Key
     */
    public static final String ERR_MSG_KEY = "errmsg";

    /**
     * Get请求参数链接符号
     */
    public static final String GET_URL_AND = "&";

    /**
     * appId参数的Key
     */
    public static final String APP_ID_KEY = "appid";

    /**
     * appSecret的Key
     */
    public static final String APP_SECRET_KEY = "secret";

    /**
     * 等于符号
     */
    public static final String EQUAL = "=";

    /**
     * 请求失败字符串
     */
    public static final String FAIL_MSG = "请求失败";

    /**
     * 存入Redis中微信Token的Key
     */
    public static final String WE_CHAT_TOKEN_KEY = "weChatToken";

    /**
     * 微信错误编码 -1 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_MINUS_ONE = "系统繁忙，此时请开发者稍候再试";

    /**
     * 微信错误编码 0 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_0 = "请求成功";

    /**
     * 微信错误编码 40001 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_40001 = "AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性";

    /**
     * 微信错误编码 40002 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_40002 = "请确保grant_type字段值为client_credential";

    /**
     * 微信错误编码 40164 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_40164 = "调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置";

    /**
     * 微信错误编码 89503 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_89503 = "此IP调用需要管理员确认,请联系管理员";

    /**
     * 微信错误编码 89501 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_89501 = "此IP正在等待管理员确认,请联系管理员";

    /**
     * 微信错误编码 89506 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_89506 = "24小时内该IP被管理员拒绝调用两次，24小时内不可再使用该IP调用";

    /**
     * 微信错误编码 89507 对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_89507 = "1小时内该IP被管理员拒绝调用一次，1小时内不可再使用该IP调用";

    /**
     * 微信其他错误编码对应信息
     */
    public static final String WE_CHAT_ERROR_CODE_OTHER = "其他原因";

    /**
     * Http请求成功码
     */
    public static final int HTTP_REQUEST_SUCCESS_CODE = 200;

    /**
     * 请求获取微信ACCESS_TOKEN
     */
    public static final String TASK_GET_WE_CHAT_TOKEN_URL = "http://laoshengle.cn/api/weChat/weChatService/getAccessToken";

    /**
     * 文件解析失败返回字符串
     */
    public static final String FILE_PARSING_FAILED_TEXT = "文件解析失败!";

    /**
     * 文件解析成功返回字符串
     */
    public static final String FILE_PARSED_SUCCESS_FULLY_TEXT = "文件解析失败!";

    /**
     * 文件上传成功返回字符串
     */
    public static final String FILE_UPLOAD_SUCCESS_TEXT = "文件上传成功!";
}
