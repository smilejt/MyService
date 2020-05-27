package cn.laoshengle.miniproject.impl.service;

import cn.laoshengle.core.entity.MiNiProjectMenuEntity;
import cn.laoshengle.core.service.miniproject.MiNiProjectMenuService;
import cn.laoshengle.miniproject.impl.mapper.MiNiProjectMenuMapper;
import cn.laoshengle.miniproject.impl.pojo.MiNiProjectMenuPojo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/24 15:09:53
 **/
@Service
@ResponseBody
@RequestMapping("api/V1/miNiProject/miNiProjectMenuService")
public class MiNiProjectMenuServiceImpl implements MiNiProjectMenuService {

    private static final Logger logger = LoggerFactory.getLogger(MiNiProjectMenuServiceImpl.class);

    @Resource
    MiNiProjectMenuMapper miNiProjectMenuMapper;

    @Override
    public List<MiNiProjectMenuEntity> getMenuByMiNiProject() {
        logger.info("[MiNiProjectMenuServiceImpl].[getMenuByMiNiProject]------> In");

        //封装查询参数
        QueryWrapper<MiNiProjectMenuPojo> query = new QueryWrapper<>();
        query.eq("enable_mark",1);
        query.eq("delete_mark",1);
        query.orderByAsc("sort");
        List<MiNiProjectMenuPojo> miNiProjectMenu = miNiProjectMenuMapper.selectList(query);

        logger.info("[MiNiProjectMenuServiceImpl].[getMenuByMiNiProject]------> miNiProjectMenu.size = {}", miNiProjectMenu == null ? 0 : miNiProjectMenu.size());
        List<MiNiProjectMenuEntity> result = new ArrayList<>();
        MiNiProjectMenuEntity entity;
        if (null != miNiProjectMenu && !miNiProjectMenu.isEmpty()){
            for (MiNiProjectMenuPojo pojo: miNiProjectMenu) {
                entity = new MiNiProjectMenuEntity();
                BeanUtils.copyProperties(pojo, entity);
                result.add(entity);
            }
        }
        return result;
    }
}
