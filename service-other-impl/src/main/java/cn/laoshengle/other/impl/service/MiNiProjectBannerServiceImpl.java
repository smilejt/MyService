package cn.laoshengle.other.impl.service;

import cn.laoshengle.core.entity.MiNiProjectBannerEntity;
import cn.laoshengle.core.service.other.MiNiProjectBannerService;
import cn.laoshengle.other.impl.mapper.MiNiProjectBannerMapper;
import cn.laoshengle.other.impl.pojo.MiNiProjectBannerPojo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/23 15:53:29
 **/
@Service
@ResponseBody
@RequestMapping("api/V1/miNiProject/miNiProjectBannerService")
public class MiNiProjectBannerServiceImpl implements MiNiProjectBannerService {

    private static final Logger logger = LoggerFactory.getLogger(MiNiProjectBannerServiceImpl.class);

    @Resource
    MiNiProjectBannerMapper miNiProjectBannerMapper;

    @Override
    public List<MiNiProjectBannerEntity> getBannerByMiNiProject() {

        logger.info("[MiNiProjectBannerServiceImpl].[getBannerByMiNiProject]------> In");

        //封装查询参数
        QueryWrapper<MiNiProjectBannerPojo> query = new QueryWrapper<>();
        query.eq("enable_mark",1);
        query.eq("delete_mark",1);
        query.orderByAsc("sort");
        List<MiNiProjectBannerPojo> miNiProjectBanner = miNiProjectBannerMapper.selectList(query);

        logger.info("[MiNiProjectBannerServiceImpl].[getBannerByMiNiProject]------> miNiProjectBanner.size = {}", miNiProjectBanner == null ? 0 : miNiProjectBanner.size());
        List<MiNiProjectBannerEntity> result = new ArrayList<>();
        MiNiProjectBannerEntity entity;
        if (null != miNiProjectBanner && !miNiProjectBanner.isEmpty()){
            for (MiNiProjectBannerPojo pojo: miNiProjectBanner) {
                entity = new MiNiProjectBannerEntity();
                BeanUtils.copyProperties(pojo, entity);
                result.add(entity);
            }
        }
        return result;
    }
}
