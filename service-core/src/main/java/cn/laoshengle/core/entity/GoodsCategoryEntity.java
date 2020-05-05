package cn.laoshengle.core.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 商品类目实体
 * @author: 龙逸
 * @createDate: 2020/05/05 14:54:52
 **/
@Data
@ToString
public class GoodsCategoryEntity implements Serializable {

    private static final long serialVersionUID = 3589482967567949726L;
    /**
     * 类目ID(本系统)
     */
    private String categoryId;

    /**
     * 类目名称(淘宝)
     */
    private String categoryName;
}
