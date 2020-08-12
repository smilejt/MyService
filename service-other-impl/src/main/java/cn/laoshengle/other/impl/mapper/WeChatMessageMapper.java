package cn.laoshengle.other.impl.mapper;

import cn.laoshengle.other.impl.pojo.WeChatMessagePojo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/04/27 22:20:35
 **/
@Mapper
@Repository
public interface WeChatMessageMapper extends BaseMapper<WeChatMessagePojo> {

    /**
     * 写入接收到的微信消息对象(不为null字段)
     *
     * @param weChatMessagePojo 待写入的微信消息实体
     * @return 受影响的行数
     */
    int insertSelective(WeChatMessagePojo weChatMessagePojo);
}
