package cn.laoshengle.local.mapper;

import cn.laoshengle.local.pojo.GoodsCategoryPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @createData: 2020-05-05 15:48
 * @author: LongJunTao
 * @Description:
 */
@Mapper
@Repository
public interface GoodsCategoryMapper {

    /**
     * 新增类目对象
     *
     * @param pojo 实体对象
     * @return 受影响条数
     */
    int insert(GoodsCategoryPojo pojo);

    /**
     * 批量新增类目
     *
     * @param pojoList 类目List
     * @return 受影响的条数
     */
    int insertByList(List<GoodsCategoryPojo> pojoList);

    /**
     * 新增非空类目
     *
     * @param pojo 实体对象
     * @return 受影响条数
     */
    int insertSelective(GoodsCategoryPojo pojo);

    /**
     * 根据条件查询类目
     *
     * @param params 查询条件
     * @return 查询结果集
     */
    List<GoodsCategoryPojo> getGoodsCategory(Map<String, Object> params);
}
