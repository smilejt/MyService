package cn.laoshengle.user.impl.mapper;

import cn.laoshengle.user.impl.pojo.ResourcesPojo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longjuntao
 * @description: 资源Mapper层
 * @date 2020/8/10 15:22
 */
@Mapper
@Repository
public interface ResourcesMapper extends BaseMapper<ResourcesPojo> {

    /**
     * 根据角色ID列表查询资源列表
     *
     * @param roleIds 角色ID列表
     * @return 资源列表
     */
    List<ResourcesPojo> selectByUserId(@Param("roleIds") List<String> roleIds);
}