package cn.laoshengle.core.entity.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 每日精选查询条件
 * @author: 龙逸
 * @createDate: 2020/05/10 15:01:43
 **/
@Data
@ToString
public class FeaturedRequestEntity implements Serializable {
    private static final long serialVersionUID = 5219451368296431613L;

    /**
     * 要查询的类目ID (全部时,不传)
     */
    private String categoryId;

    /**
     * 查询的商品名称
     */
    private String search;

    /**
     * 当前页数(默认为1)
     */
    private Integer pageIndex;

    /**
     * 每页显示数量(默认10)
     */
    private Integer pageSize;
}
