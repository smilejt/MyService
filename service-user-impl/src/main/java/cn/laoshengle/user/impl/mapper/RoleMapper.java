package cn.laoshengle.user.impl.mapper;

import cn.laoshengle.user.impl.pojo.RolePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longjuntao
 * @description: 角色Mapper层
 * @date 2020/8/11 15:22
 */
@Mapper
@Repository
public interface RoleMapper {

    /**
     * 根据用户ID查询角色信息
     * @param userId 用户ID
     * @return 角色信息
     */
    List<RolePojo> selectByUserId(@Param("userId") String userId);
}