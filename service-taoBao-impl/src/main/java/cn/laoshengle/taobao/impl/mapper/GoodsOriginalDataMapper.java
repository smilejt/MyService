package cn.laoshengle.taobao.impl.mapper;

import cn.laoshengle.taobao.impl.pojo.GoodsOriginalDataPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
}