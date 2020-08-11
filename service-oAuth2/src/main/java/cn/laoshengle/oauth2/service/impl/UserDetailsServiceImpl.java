package cn.laoshengle.oauth2.service.impl;

import cn.laoshengle.core.entity.user.SystemUserEntity;
import cn.laoshengle.core.service.user.UserService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author longjuntao
 * @description: 自定义认证与授权
 * @date 2020/8/10 10:06
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {

        //获取登录用户信息
        logger.info("[UserDetailsServiceImpl].[loadUserByUsername]------> Spring Security User Approve Start，loginName = {}", loginName);
        SystemUserEntity systemUserEntity = userService.getUserByLoginName(loginName);
        logger.info("[UserDetailsServiceImpl].[loadUserByUsername]------> systemUserEntity = {}", JSON.toJSONString(systemUserEntity));
        if (StringUtils.isEmpty(systemUserEntity)){
            logger.info("[UserDetailsServiceImpl].[loadUserByUsername]------> Not Found User By LoginName");
            throw new UsernameNotFoundException(loginName);
        }

        //获取角色对应的资源

        return null;
    }
}
