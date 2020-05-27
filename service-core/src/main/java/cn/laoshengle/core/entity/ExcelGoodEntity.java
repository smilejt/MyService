package cn.laoshengle.core.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/27 20:17:52
 **/
@Data
@ToString
public class ExcelGoodEntity {

    /**
     * 商品ID(淘宝)
     */
    @ExcelProperty(index = 0)
    private String goodId;

    /**
     * 商品名
     */
    @ExcelProperty(index = 1)
    private String goodName;

    /**
     * 商品主图URL
     */
    @ExcelProperty(index = 2)
    private String goodPicUrl;

    /**
     * 商品详细URL
     */
    @ExcelProperty(index = 3)
    private String goodDetailedUrl;

    /**
     * 商品一级类目名称
     */
    @ExcelProperty(index = 4)
    private String goodCategory;

    /**
     * 淘宝客URL
     */
    @ExcelProperty(index = 5)
    private String taoBaoKeUrl;

    /**
     * 商品价格(元)
     */
    @ExcelProperty(index = 6)
    private String goodPrice;

    /**
     * 商品月销量
     */
    @ExcelProperty(index = 7)
    private String goodSalesMonth;

    /**
     * 收入比例(百分之)
     */
    @ExcelProperty(index = 8)
    private String incomeRatio;

    /**
     * 商品佣金(元)
     */
    @ExcelProperty(index = 9)
    private String goodCommission;

    /**
     * 卖家旺旺名
     */
    @ExcelProperty(index = 10)
    private String sellerWangName;

    /**
     * 卖家ID
     */
    @ExcelProperty(index = 11)
    private String sellerId;

    /**
     * 店铺名称
     */
    @ExcelProperty(index = 12)
    private String shopName;

    /**
     * 平台类型
     */
    @ExcelProperty(index = 13)
    private String platformType;

    /**
     * 优惠券ID
     */
    @ExcelProperty(index = 14)
    private String couponId;

    /**
     * 优惠券总量
     */
    @ExcelProperty(index = 15)
    private String couponCount;

    /**
     * 优惠券剩余
     */
    @ExcelProperty(index = 16)
    private String couponNum;

    /**
     * 优惠券额度
     */
    @ExcelProperty(index = 17)
    private String couponQuota;

    /**
     * 优惠券开始时间
     */
    @ExcelProperty(index = 18)
    private String couponStartTime;

    /**
     * 优惠券结束时间
     */
    @ExcelProperty(index = 19)
    private String couponEndTime;

    /**
     * 优惠券URL
     */
    @ExcelProperty(index = 20)
    private String couponUrl;

    /**
     * 商品优惠券推广URL
     */
    @ExcelProperty(index = 21)
    private String goodCouponPromoteUrl;
}
