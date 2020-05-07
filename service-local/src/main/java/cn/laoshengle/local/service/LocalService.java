package cn.laoshengle.local.service;

import cn.laoshengle.core.entity.GoodsOriginalDataEntity;

import java.util.List;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/07 20:42:12
 **/
public interface LocalService {

    /**
     * 本地处理上传数据方法
     * @param paramsList Excel解析出来的列表
     * @return 新增结果
     */
    Boolean insertTaoBaoFeaturedByEveryDay(List<GoodsOriginalDataEntity> paramsList);
}
