package cn.laoshengle.core.enums;

/**
 * @description: 微信消息类型
 * @author: 龙逸
 * @createDate: 2020/04/27 15:06:39
 **/
public enum WeChatMsgType {
    /**
     * msgType对应的消息类型
     */
    text("文本消息"), event("关注/取消关注"), image("图片消息"), voice("语音消息"), video("视频消息"), shortvideo("小视频消息"), location("地理位置消息"), link("链接消息");

    private String date;

    private WeChatMsgType(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
