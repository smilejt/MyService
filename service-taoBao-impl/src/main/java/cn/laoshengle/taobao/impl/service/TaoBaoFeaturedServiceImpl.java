package cn.laoshengle.taobao.impl.service;

import cn.laoshengle.core.entity.GoodsOriginalDataEntity;
import cn.laoshengle.core.service.taobao.TaoBaoFeaturedService;
import cn.laoshengle.taobao.impl.mapper.GoodsCategoryMapper;
import cn.laoshengle.taobao.impl.mapper.GoodsOriginalDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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


    }
}
