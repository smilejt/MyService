package cn.laoshengle.miniproject.impl.mapper;

import cn.laoshengle.miniproject.impl.pojo.MiNiProjectBannerPojo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/23 17:51:18
 **/
@Mapper
@Repository
public interface MiNiProjectBannerMapper extends BaseMapper<MiNiProjectBannerPojo> {

    /**
     * 新增非空类目
     *
     * @param pojo 实体对象
     * @return 受影响条数
     */
    int insertSelective(MiNiProjectBannerPojo pojo);

    /**
     * 根据条件查询类目
     *
     * @param params 查询条件
     * @return 查询结果集
     */
    List<MiNiProjectBannerPojo> getBannerListByParams(Map<String, Object> params);
}
