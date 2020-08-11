package cn.laoshengle.user.impl.service;

import cn.laoshengle.core.entity.user.SystemUserEntity;
import cn.laoshengle.core.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author longjuntao
 * @description: 用户
 * @date 2020/8/10 15:26
 */
@Service
@ResponseBody
@RequestMapping("api/V1/user/userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SystemUserEntity getUserByLoginName(String loginName) {
        return null;
    }
}
