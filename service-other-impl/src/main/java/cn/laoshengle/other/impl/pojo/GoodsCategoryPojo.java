package cn.laoshengle.other.impl.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @description: 商品类目实体
 * @author: 龙逸
 * @createDate: 2020/05/05 14:54:52
 **/
@Data
@ToString
public class GoodsCategoryPojo {

    /**
     * 类目ID(本系统)
     */
    private String categoryId;

    /**
     * 类目名称(淘宝)
     */
    private String categoryName;
}
