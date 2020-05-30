package cn.laoshengle.local.service.impl;

import cn.laoshengle.core.entity.CouponAmountUtilEntity;
import cn.laoshengle.core.entity.GoodsOriginalDataEntity;
import cn.laoshengle.core.utils.CouponUtil;
import cn.laoshengle.local.mapper.GoodsCategoryMapper;
import cn.laoshengle.local.mapper.GoodsOriginalDataMapper;
import cn.laoshengle.local.pojo.GoodsCategoryPojo;
import cn.laoshengle.local.pojo.GoodsOriginalDataPojo;
import cn.laoshengle.local.service.LocalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/07 20:42:52
 **/
@Service
public class LocalServiceImpl implements LocalService {

    private static final Logger logger = LoggerFactory.getLogger(LocalServiceImpl.class);

    @Resource
    GoodsOriginalDataMapper goodsOriginalDataMapper;

    @Resource
    GoodsCategoryMapper goodsCategoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertTaoBaoFeaturedByEveryDay(List<GoodsOriginalDataEntity> paramsList) {
        //类目查询参数
        Map<String, Object> tempMap = new HashMap<>();
        List<GoodsCategoryPojo> resultCategory = goodsCategoryMapper.getGoodsCategory(tempMap);
        List<GoodsCategoryPojo> addCategory = new ArrayList<>();
        Map<String, String> categoryMap = new HashMap<>();
        if (resultCategory != null) {
            for (GoodsCategoryPojo pojo : resultCategory) {
                categoryMap.put(pojo.getCategoryName(), pojo.getCategoryId());
            }
        }

        if (!paramsList.isEmpty()) {

            List<GoodsOriginalDataPojo> paramList = new ArrayList<>();
            GoodsOriginalDataPojo pojo;
            int num = 0, batch = 0;

            //循环参入的的商品列表
//            for (GoodsOriginalDataEntity entity : paramsList) {
            for (int i = 0; i < paramsList.size(); i++) {
                GoodsOriginalDataEntity entity = paramsList.get(i);
                pojo = new GoodsOriginalDataPojo();
                BeanUtils.copyProperties(entity, pojo);
                //设置系统ID
                pojo.setSystemId(UUID.randomUUID().toString().replace("-", ""));
                //记录写入时间(此处为系统处理时间)
                pojo.setInsertTime(new Date());
                if (categoryMap.containsKey(pojo.getGoodCategory())) {
                    //系统存在该类目,关联类目ID
                    pojo.setGoodCategoryId(categoryMap.get(pojo.getGoodCategory()));
                } else {
                    //当前系统不存在该类目,新增类目
                    GoodsCategoryPojo categoryPojo = new GoodsCategoryPojo();
                    String categoryId = UUID.randomUUID().toString().replace("-", "");
                    categoryPojo.setCategoryId(categoryId);
                    categoryPojo.setCategoryName(pojo.getGoodCategory());
                    addCategory.add(categoryPojo);
                    categoryMap.put(categoryPojo.getCategoryName(), categoryPojo.getCategoryId());
                    //关联类目ID
                    pojo.setGoodCategoryId(categoryId);
                }
                //将double数据转换为Long(Double为元,Long为分)
                pojo.setGoodPriceSmall(Math.round(pojo.getGoodPrice() * 100));
                //设置收入比例(万分之)
                pojo.setIncomeRatioSmall(Math.round(pojo.getIncomeRatio() * 100));
                //设置佣金金额(分)
                pojo.setGoodCommissionSmall(Math.round(pojo.getGoodCommission() * 100));
                //拆分优惠券
                CouponAmountUtilEntity couponAmountUtilEntity = CouponUtil.splitCouponString(pojo.getCouponQuota());
                //写入拆分后的优惠券门槛金额(分)
                pojo.setCouponStartAmount(couponAmountUtilEntity.getCouponStartAmount());
                //写入拆分后的优惠券折扣金额(分)
                pojo.setCouponDiscountAmount(couponAmountUtilEntity.getCouponDiscountAmount());
                paramList.add(pojo);

                //计数当前是多少个
                num++;

                //3000个为一批新增到数据库
                if (num >= 3000 || (i == paramsList.size()-1)) {
                    batch++;
                    if (goodsOriginalDataMapper.insertByList(paramList) <= 0) {
                        logger.error("[TaoBaoFeaturedServiceImpl].[insertTaoBaoFeaturedByEveryDay]------> Article {} to {} failed to insert", (batch - 1) * 500, batch * 500);
                    }
                    //不管是否插入成功,清空之前的记录
                    num = 0;
                    paramList = new ArrayList<>();
                }
            }
        }

        //判断类目是否有新增
        if (!addCategory.isEmpty()) {
            if (goodsCategoryMapper.insertByList(addCategory) <= 0) {
                logger.error("[TaoBaoFeaturedServiceImpl].[insertTaoBaoFeaturedByEveryDay]------> Category insertion failed");
            }
        }
        return true;
    }
}
