package cn.laoshengle.taobao.impl.mapper;

import cn.laoshengle.taobao.impl.pojo.GoodsCategoryPojo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @createData: 2020-05-05 15:48
 * @author: LongJunTao
 * @Description:
 */
@Mapper
@Repository
public interface GoodsCategoryMapper {

    int insert(GoodsCategoryPojo pojo);

    int insertSelective(GoodsCategoryPojo pojo);
}
