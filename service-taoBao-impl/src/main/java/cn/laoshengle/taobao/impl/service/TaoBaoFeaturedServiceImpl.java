package cn.laoshengle.taobao.impl.service;

import cn.laoshengle.core.entity.GoodsOriginalDataEntity;
import cn.laoshengle.core.service.taobao.TaoBaoFeaturedService;
import cn.laoshengle.taobao.impl.mapper.GoodsCategoryMapper;
import cn.laoshengle.taobao.impl.mapper.GoodsOriginalDataMapper;
import cn.laoshengle.taobao.impl.pojo.GoodsCategoryPojo;
import cn.laoshengle.taobao.impl.pojo.GoodsOriginalDataPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/05 15:24:38
 **/
@Service
@ResponseBody
@RequestMapping("api/V1/taoBao/taoBaoFeaturedService")
public class TaoBaoFeaturedServiceImpl implements TaoBaoFeaturedService {

    private static final Logger logger = LoggerFactory.getLogger(TaoBaoFeaturedServiceImpl.class);

    @Resource
    GoodsOriginalDataMapper goodsOriginalDataMapper;

    @Resource
    GoodsCategoryMapper goodsCategoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertTaoBaoFeaturedByEveryDay(List<GoodsOriginalDataEntity> paramsList) {
        logger.info("[TaoBaoFeaturedServiceImpl].[insertTaoBaoFeaturedByEveryDay]------> In ParamsList Num = {}", paramsList.size());

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
            int num = 0;

            //循环参入的的商品列表
            for (GoodsOriginalDataEntity entity : paramsList) {
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

                paramList.add(pojo);
            }
        }
    }
}
