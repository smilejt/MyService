package cn.laoshengle.core.service.taobao;

import cn.laoshengle.core.constant.FeignConstant;
import cn.laoshengle.core.entity.GoodsCategoryEntity;
import cn.laoshengle.core.entity.GoodsOriginalDataEntity;
import cn.laoshengle.core.entity.request.FeaturedRequestEntity;
import cn.laoshengle.core.entity.request.ListEntity;
import cn.laoshengle.core.service.taobao.impl.TaoBaoFeaturedServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description: 淘宝精选推广商品
 * @author: 龙逸
 * @createDate: 2020/05/05 13:14:21
 **/
@Service
@FeignClient(value = FeignConstant.TAO_BAO_SERVICE_NAME, contextId = "taoBaoFeaturedService", path = "api/V1/taoBao/taoBaoFeaturedService", fallback = TaoBaoFeaturedServiceFallBackImpl.class)
public interface TaoBaoFeaturedService {

    /**
     * 写入获取完成的淘宝每日推荐数据
     *
     * @param paramsList 从EXCEL中读取的数据
     */
    @PostMapping("insertTaoBaoFeaturedByEveryDay")
    void insertTaoBaoFeaturedByEveryDay(@RequestBody ListEntity paramsList);

    /**
     * 获取所有类目列表
     *
     * @return 类目列表
     */
    @PostMapping("getAllCategory")
    List<GoodsCategoryEntity> getAllCategory();

    /**
     * 根据条件查询每日精选商品列表(分页)
     * 每天10点x分之前查询的是前一天的数据,10点x分之后查询的是当天导入的数据
     *
     * @param params 查询参数
     * @return 查询结果
     */
    @PostMapping("getFeaturedByParams")
    List<GoodsOriginalDataEntity> getFeaturedByParams(@RequestBody FeaturedRequestEntity params);

    /**
     * 据条件查询每日精选商品条数
     * 每天10点x分之前查询的是前一天的数据,10点x分之后查询的是当天导入的数据
     *
     * @param params 查询参数
     * @return 统计结果
     */
    @PostMapping("countFeaturedByParams")
    Long countFeaturedByParams(@RequestBody FeaturedRequestEntity params);
}
