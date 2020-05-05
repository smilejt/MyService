package cn.laoshengle.taobao.impl.service;

import cn.laoshengle.core.entity.GoodsOriginalDataEntity;
import cn.laoshengle.core.service.taobao.TaoBaoFeaturedService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void insertTaoBaoFeaturedByEveryDay(List<GoodsOriginalDataEntity> paramsList) {

    }
}
