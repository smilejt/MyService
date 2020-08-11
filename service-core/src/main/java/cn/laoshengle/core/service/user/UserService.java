package cn.laoshengle.core.service.user;

import cn.laoshengle.core.constant.FeignConstant;
import cn.laoshengle.core.entity.user.SystemUserEntity;
import cn.laoshengle.core.service.user.impl.UserServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author longjuntao
 * @description:
 * @date 2020/8/10 11:45
 */
@FeignClient(value = FeignConstant.USER_SERVICE_NAME, contextId = "userService", path = "api/V1/user/userService", fallback = UserServiceFallBackImpl.class)
public interface UserService {

    /**
     * 根据用户登录名查询用户信息
     *
     * @param loginName 用户登录名
     * @return 查询结果
     */
    @PostMapping("getUserByLoginName")
    SystemUserEntity getUserByLoginName(@RequestParam("loginName") String loginName);

}
