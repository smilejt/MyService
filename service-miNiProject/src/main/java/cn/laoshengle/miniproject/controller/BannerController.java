package cn.laoshengle.miniproject.controller;

import cn.laoshengle.core.entity.JsonResult;
import cn.laoshengle.core.entity.MiNiProjectBannerEntity;
import cn.laoshengle.core.service.miniproject.MiNiProjectBannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 微信小程序Banner处理的Controller
 * @author: 龙逸
 * @createDate: 2020/05/21 19:27:38
 **/
@RestController
@RequestMapping(value = "bannerService")
public class BannerController {

    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Resource
    MiNiProjectBannerService miNiProjectBannerService;

    /**
     * 微信小程序端获取Banner图
     *
     * @return 查询到的Banner信息
     */
    @GetMapping("getBannerByMiNiProject")
    public JsonResult getBannerByMiNiProject() {

        logger.info("[BannerController].[getBannerByMiNiProject]------> In");

        List<MiNiProjectBannerEntity> bannerByMiNiProject = miNiProjectBannerService.getBannerByMiNiProject();

        logger.info("[BannerController].[getBannerByMiNiProject]------> Select Result Num = {}", bannerByMiNiProject == null ? 0 : bannerByMiNiProject.size());
        return JsonResult.buildSuccess("data", bannerByMiNiProject);
    }
}
