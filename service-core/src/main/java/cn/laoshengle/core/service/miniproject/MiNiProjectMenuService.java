package cn.laoshengle.core.service.miniproject;

import cn.laoshengle.core.constant.FeignConstant;
import cn.laoshengle.core.entity.MiNiProjectMenuEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @description: 小程序的Banner相关处理
 * @author: 龙逸
 * @createDate: 2020/05/23 15:16:31
 **/
@Service
@FeignClient(value = FeignConstant.MINI_PROJECT_SERVICE_NAME, contextId = "miNiProjectMenuService", path = "api/V1/miNiProject/miNiProjectMenuService")
public interface MiNiProjectMenuService {

    /**
     * 微信小程序获取Banner
     *
     * @return 要展示的Banner信息
     */
    @PostMapping("getMenuByMiNiProject")
    List<MiNiProjectMenuEntity> getMenuByMiNiProject();
}
