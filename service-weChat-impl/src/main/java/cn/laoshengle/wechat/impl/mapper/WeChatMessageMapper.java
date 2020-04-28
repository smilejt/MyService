package cn.laoshengle.wechat.impl.mapper;

import cn.laoshengle.wechat.impl.pojo.WeChatMessagePojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/04/27 22:20:35
 **/
@Mapper
@Repository
public interface WeChatMessageMapper {

    /**
     * 写入接收到的微信消息对象(全字段)
     *
     * @param weChatMessagePojo 待写入的微信消息实体
     * @return 受影响的行数
     */
    int insert(WeChatMessagePojo weChatMessagePojo);

    /**
     * 写入接收到的微信消息对象(不为null字段)
     *
     * @param weChatMessagePojo 待写入的微信消息实体
     * @return 受影响的行数
     */
    int insertSelective(WeChatMessagePojo weChatMessagePojo);
}
