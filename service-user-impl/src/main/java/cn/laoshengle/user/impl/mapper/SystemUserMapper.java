package cn.laoshengle.user.impl.mapper;

import cn.laoshengle.core.entity.request.user.QueryUserForm;
import cn.laoshengle.core.entity.user.SystemUserEntity;
import cn.laoshengle.user.impl.pojo.SystemUserPojo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author longjuntao
 * @description: 用户Mapper层
 * @date 2020/8/10 15:22
 */
@Mapper
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUserPojo> {

    /**
     *  分页查询用户
     * @param page 分页参数
     * @param queryUserForm 查询参数
     * @return 查询结果
     */
    Page<SystemUserEntity> getUserPage(IPage<QueryUserForm> page, @Param("queryUserForm") QueryUserForm queryUserForm);
}
