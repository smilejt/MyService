package cn.laoshengle.other.impl.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description: 淘宝每日精选数据实体
 * @author: 龙逸
 * @createDate: 2020/05/05 14:57:51
 **/
@Data
@ToString
public class GoodsOriginalDataPojo {

    /**
     * 商品ID(本系统)
     */
    private String systemId;

    /**
     * 商品ID(淘宝)
     */
    private Long goodId;

    /**
     * 商品名
     */
    private String goodName;

    /**
     * 商品主图URL
     */
    private String goodPicUrl;

    /**
     * 商品详细URL
     */
    private String goodDetailedUrl;

    /**
     * 商品一级类目名称
     */
    private String goodCategory;

    /**
     * 商品类目ID(本系统)
     */
    private String goodCategoryId;

    /**
     * 淘宝客URL
     */
    private String taoBaoKeUrl;

    /**
     * 商品价格(元)
     */
    private Double goodPrice;

    /**
     * 商品价格(分)
     */
    private Long goodPriceSmall;

    /**
     * 商品月销量
     */
    private Integer goodSalesMonth;

    /**
     * 收入比例(百分之)
     */
    private Double incomeRatio;

    /**
     * 收入比例(万分之)
     */
    private Long incomeRatioSmall;

    /**
     * 商品佣金(元)
     */
    private Double goodCommission;

    /**
     * 商品佣金(分)
     */
    private Long goodCommissionSmall;

    /**
     * 卖家旺旺名
     */
    private String sellerWangName;

    /**
     * 卖家ID
     */
    private Long sellerId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 平台类型
     */
    private String platformType;

    /**
     * 优惠券ID
     */
    private String couponId;

    /**
     * 优惠券总量
     */
    private Integer couponCount;

    /**
     * 优惠券剩余
     */
    private Integer couponNum;

    /**
     * 优惠券额度
     */
    private String couponQuota;

    /**
     * 优惠券门槛金额(分)
     */
    private Long couponStartAmount;

    /**
     * 优惠券折扣金额(分)
     */
    private Long couponDiscountAmount;

    /**
     * 优惠券开始时间
     */
    private String couponStartTime;

    /**
     * 优惠券结束时间
     */
    private String couponEndTime;

    /**
     * 优惠券URL
     */
    private String couponUrl;

    /**
     * 商品优惠券推广URL
     */
    private String goodCouponPromoteUrl;

    /**
     * 写入时间
     */
    private Date insertTime;
}
