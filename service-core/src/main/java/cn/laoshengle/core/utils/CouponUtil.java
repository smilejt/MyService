package cn.laoshengle.core.utils;

import cn.laoshengle.core.entity.CouponAmountUtilEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @description: 优惠券拆分对象
 * @author: 龙逸
 * @createDate: 2020/05/07 13:41:12
 **/
public class CouponUtil {

    private static final Logger logger = LoggerFactory.getLogger(CouponUtil.class);

    private static final String COUPON_PREFIX = "满";
    private static final String SEPARATION = "减";
    private static final String CHINESE_YUAN = "元";
    private static final String UNCONDITIONAL = "元无条件券";
    private static final String NULL_STRING = "";

    /**
     * 根据优惠券内容拆分优惠券信息
     *
     * @param couponString 优惠券字符串
     * @return 拆分结果对象
     */
    public static CouponAmountUtilEntity splitCouponString(String couponString) {
        if (!StringUtils.isEmpty(couponString)) {
            try {
                //判断是否是无条件券
                if (couponString.contains(UNCONDITIONAL)) {
                    long couponDiscountAmount = Long.parseLong(couponString.replace(UNCONDITIONAL, NULL_STRING));
                    return new CouponAmountUtilEntity(0L, couponDiscountAmount);
                } else {
                    //去掉字符串中的'满'和'元'
                    couponString = couponString.replace(COUPON_PREFIX, NULL_STRING).replace(CHINESE_YUAN, NULL_STRING);
                    String[] couponArray = couponString.split(SEPARATION);
                    return new CouponAmountUtilEntity(Long.parseLong(couponArray[0]) * 100, Long.parseLong(couponArray[1]) * 100);
                }
            } catch (Exception e) {
                logger.error("[CouponUtil].[splitCouponString]------> 优惠券拆分异常:", e);
                return new CouponAmountUtilEntity(0L, 0L);
            }
        }
        //优惠券字段为空,初始化优惠金额为0
        return new CouponAmountUtilEntity(0L, 0L);
    }
}
