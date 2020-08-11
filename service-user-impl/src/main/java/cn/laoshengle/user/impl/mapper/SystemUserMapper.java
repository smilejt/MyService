package cn.laoshengle.user.impl.mapper;

import cn.laoshengle.user.impl.pojo.SystemUserPojo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author longjuntao
 * @description: 用户Mapper层
 * @date 2020/8/10 15:22
 */
@Mapper
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUserPojo> {
}
