USE `service_taobao`;
# 淘宝每日精选原始数据表
DROP TABLE IF EXISTS `service_taobao`.`taobao_goods_original_data`;
CREATE TABLE `service_taobao`.`taobao_goods_original_data`
(
    `system_id`               VARCHAR(32)    NOT NULL COMMENT '系统ID',
    `good_id`                 BIGINT(20)     NOT NULL COMMENT '商品ID(淘宝)',
    `good_name`               VARCHAR(255)   NULL COMMENT '商品名',
    `good_pic_url`            VARCHAR(1000)  NULL COMMENT '商品主图',
    `good_detailed_url`       VARCHAR(1000)  NULL COMMENT '商品详情URL',
    `good_category`           VARCHAR(50)    NULL COMMENT '商品一级类目',
    `good_category_id`        VARCHAR(32)    NULL COMMENT '商品类目ID(本系统)',
    `tao_bao_ke_url`          VARCHAR(1000)  NULL COMMENT '淘宝客URL',
    `good_price`              DECIMAL(20, 2) NULL COMMENT '商品价格(元)',
    `good_price_small`        BIGINT(25)     NULL COMMENT '商品价格(分)',
    `good_sales_month`        BIGINT(10)     NULL COMMENT '商品月销量',
    `income_ratio`            DECIMAL(10, 2) NULL COMMENT '收入比例(百分之)',
    `income_ratio_small`      BIGINT(15)     NULL COMMENT '收入比例(万分之)',
    `good_commission`         DECIMAL(20, 2) NULL COMMENT '商品佣金(元)',
    `good_commission_small`   BIGINT(25)     NULL COMMENT '商品佣金(分)',
    `seller_wang_name`        VARCHAR(255)   NULL COMMENT '卖家旺旺名',
    `seller_id`               BIGINT(20)     NULL COMMENT '卖家ID',
    `shop_name`               VARCHAR(255)   NULL COMMENT '店铺名称',
    `platform_type`           VARCHAR(10)    NULL COMMENT '平台类型',
    `coupon_id`               VARCHAR(50)    NULL COMMENT '优惠券ID',
    `coupon_count`            BIGINT(10)     NULL COMMENT '优惠券总量',
    `coupon_num`              BIGINT(10)     NULL COMMENT '优惠券剩余',
    `coupon_quota`            VARCHAR(20)    NULL COMMENT '优惠券额度',
    `coupon_start_amount`     BIGINT(25)     NULL COMMENT '优惠券门槛金额(分)',
    `coupon_discount_amount`  BIGINT(25)     NULL COMMENT '优惠券折扣金额(分)',
    `coupon_start_time`       VARCHAR(20)    NULL COMMENT '优惠券开始时间',
    `coupon_end_time`         VARCHAR(20)    NULL COMMENT '优惠券结束时间',
    `coupon_url`              VARCHAR(1000)  NULL COMMENT '优惠券URL',
    `good_coupon_promote_url` VARCHAR(1000)  NULL COMMENT '商品优惠券推广URL',
    `insert_time`             DATETIME       NULL COMMENT '写入时间',
    primary key (`system_id`)
) COMMENT = '淘宝每日精选原数据';

# 淘宝商品一级类目
DROP TABLE IF EXISTS `service_taobao`.`taobao_goods_category`;
CREATE TABLE `service_taobao`.`taobao_goods_category`
(
    `category_id`   VARCHAR(32) NOT NULL COMMENT '类目ID',
    `category_name` VARCHAR(50) NULL COMMENT '类目名称',
    primary key (`category_id`)
) COMMENT = '淘宝数据拆分后的类目表';