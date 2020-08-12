package cn.laoshengle.other.impl.mapper;

import cn.laoshengle.other.impl.pojo.GoodsOriginalDataPojo;
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
public interface GoodsOriginalDataMapper {
    /**
     * 根据ID删除
     *
     * @param systemId 主键ID
     * @return 受影响条数
     */
    int deleteByPrimaryKey(String systemId);

    /**
     * 全字段新增
     *
     * @param record 新增对象
     * @return 受影响条数
     */
    int insert(GoodsOriginalDataPojo record);

    /**
     * 非空字段新增
     *
     * @param record 新增对象
     * @return 受影响条数
     */
    int insertSelective(GoodsOriginalDataPojo record);

    /**
     * 根据主键查询
     *
     * @param systemId 主键ID
     * @return 查询结果
     */
    GoodsOriginalDataPojo selectByPrimaryKey(String systemId);

    /**
     * 根据主键更新非空字段
     *
     * @param record 更新对象
     * @return 受影响条数
     */
    int updateByPrimaryKeySelective(GoodsOriginalDataPojo record);

    /**
     * 根据主键全字段更新
     *
     * @param record 更新对象
     * @return 受影响条数
     */
    int updateByPrimaryKey(GoodsOriginalDataPojo record);

    /**
     * 批量全字段新增
     *
     * @param recordList 批量新增的List
     * @return 新增条数
     */
    int insertByList(List<GoodsOriginalDataPojo> recordList);

    /**
     * 根据入参查询列表
     *
     * @param paramsMap 入参Map对象
     * @return 查询结果集
     */
    List<GoodsOriginalDataPojo> getFeaturedByParams(Map<String, Object> paramsMap);

    /**
     * 根据入参查询数据条数
     *
     * @param paramsMap 入参Map对象
     * @return 统计条数结果
     */
    Long countFeaturedByParams(Map<String, Object> paramsMap);
}