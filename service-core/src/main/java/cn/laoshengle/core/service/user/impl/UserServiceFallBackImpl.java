package cn.laoshengle.core.service.user.impl;

import cn.laoshengle.core.entity.request.user.AddUserForm;
import cn.laoshengle.core.entity.request.user.QueryUserForm;
import cn.laoshengle.core.entity.user.SystemUserEntity;
import cn.laoshengle.core.service.user.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author longjuntao
 * @description:
 * @date 2020/8/10 14:18
 */
public class UserServiceFallBackImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceFallBackImpl.class);

    @Override
    public SystemUserEntity getUserByLoginName(String loginName) {
        logger.warn("[UserServiceFallBackImpl].[getUserByLoginName]------> Request User Service Fail");
        return null;
    }

    @Override
    public boolean addUser(AddUserForm addUserForm) {
        logger.warn("[UserServiceFallBackImpl].[addUser]------> Request User Service Fail");
        return false;
    }

    @Override
    public Page<SystemUserEntity> getUserPage(QueryUserForm queryUserForm) {
        logger.warn("[UserServiceFallBackImpl].[getUserPage]------> Request User Service Fail");
        return null;
    }
}
