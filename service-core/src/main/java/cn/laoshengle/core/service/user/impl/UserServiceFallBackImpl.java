package cn.laoshengle.core.service.user.impl;

import cn.laoshengle.core.entity.user.SystemUserEntity;
import cn.laoshengle.core.service.user.UserService;

/**
 * @author longjuntao
 * @description:
 * @date 2020/8/10 14:18
 */
public class UserServiceFallBackImpl implements UserService {
    @Override
    public SystemUserEntity getUserByLoginName(String loginName) {
        return null;
    }
}
