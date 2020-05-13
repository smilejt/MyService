package cn.laoshengle.core.service.taobao;

import cn.laoshengle.core.constant.FeignConstant;
import cn.laoshengle.core.entity.GoodsCategoryEntity;
import cn.laoshengle.core.entity.request.ListEntity;
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
@FeignClient(value = FeignConstant.TAO_BAO_SERVICE_NAME, contextId = "taoBaoFeaturedService", path = "api/V1/taoBao/taoBaoFeaturedService")
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
}
