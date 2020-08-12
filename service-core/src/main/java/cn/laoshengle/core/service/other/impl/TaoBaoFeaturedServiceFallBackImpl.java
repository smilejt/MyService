package cn.laoshengle.core.service.other.impl;

import cn.laoshengle.core.entity.GoodsCategoryEntity;
import cn.laoshengle.core.entity.GoodsOriginalDataEntity;
import cn.laoshengle.core.entity.request.FeaturedRequestEntity;
import cn.laoshengle.core.entity.request.ListEntity;
import cn.laoshengle.core.service.other.TaoBaoFeaturedService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/30 13:55:50
 **/
@Component
public class TaoBaoFeaturedServiceFallBackImpl implements TaoBaoFeaturedService {
    @Override
    public void insertTaoBaoFeaturedByEveryDay(ListEntity paramsList) {
    }

    @Override
    public List<GoodsCategoryEntity> getAllCategory() {
        return null;
    }

    @Override
    public List<GoodsOriginalDataEntity> getFeaturedByParams(FeaturedRequestEntity params) {
        return null;
    }

    @Override
    public Long countFeaturedByParams(FeaturedRequestEntity params) {
        return null;
    }
}
