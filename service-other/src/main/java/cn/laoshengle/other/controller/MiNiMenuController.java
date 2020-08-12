package cn.laoshengle.other.controller;

import cn.laoshengle.core.entity.JsonResult;
import cn.laoshengle.core.entity.MiNiProjectMenuEntity;
import cn.laoshengle.core.service.other.MiNiProjectMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 微信小程序首页菜单列表
 * @author: 龙逸
 * @createDate: 2020/05/24 14:56:59
 **/
@RestController
@RequestMapping(value = "menuService")
public class MiNiMenuController {

    private static final Logger logger = LoggerFactory.getLogger(MiNiMenuController.class);

    @Resource
    MiNiProjectMenuService miNiProjectMenuService;

    @GetMapping("getMenuByMiNiProject")
    public JsonResult getMenuByMiNiProject() {

        logger.info("[MenuController].[getMenuByMiNiProject]------> In");

        List<MiNiProjectMenuEntity> menuByMiNiProject = miNiProjectMenuService.getMenuByMiNiProject();

        logger.info("[MenuController].[getMenuByMiNiProject]------> Select Result Num = {}", menuByMiNiProject == null ? 0 : menuByMiNiProject.size());
        return JsonResult.buildSuccess("data", menuByMiNiProject);
    }
}
