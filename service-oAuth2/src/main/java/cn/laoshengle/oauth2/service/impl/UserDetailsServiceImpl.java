package cn.laoshengle.oauth2.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author longjuntao
 * @description: 自定义认证与授权
 * @date 2020/8/10 10:06
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        logger.info("[UserDetailsServiceImpl].[loadUserByUsername]--------> Spring Security用户认证开始，userName = {}", userName);

        return null;
    }
}
