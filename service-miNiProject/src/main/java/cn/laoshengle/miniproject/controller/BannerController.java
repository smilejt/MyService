package cn.laoshengle.miniproject.controller;

import cn.laoshengle.core.entity.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 微信小程序Banner处理的Controller
 * @author: 龙逸
 * @createDate: 2020/05/21 19:27:38
 **/
@RestController
@RequestMapping(value = "bannerService")
public class BannerController {

    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

    /**
     *  微信小程序端获取Banner图
     * @return 查询到的Banner信息
     */
    @GetMapping("getBannerByMiNiProject")
    public JsonResult getBannerByMiNiProject() {

        return null;
    }
}
