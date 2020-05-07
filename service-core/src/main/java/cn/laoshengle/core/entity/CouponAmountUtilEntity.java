package cn.laoshengle.core.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: couponUtil拆分后的优惠券金额对象
 * @author: 龙逸
 * @createDate: 2020/05/07 13:49:31
 **/
@Data
@ToString
public class CouponAmountUtilEntity implements Serializable {

    public CouponAmountUtilEntity() {
    }

    public CouponAmountUtilEntity(long couponStartAmount, long couponDiscountAmount) {
        this.couponStartAmount = couponStartAmount;
        this.couponDiscountAmount = couponDiscountAmount;
    }

    private static final long serialVersionUID = -6219857311285500631L;

    /**
     * 优惠券使用门槛金额(分)
     */
    private Long couponStartAmount;

    /**
     * 优惠券折扣金额(分)
     */
    private Long couponDiscountAmount;
}
