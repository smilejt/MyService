package cn.laoshengle.user.impl.service;

import cn.laoshengle.core.entity.user.SystemUserEntity;
import cn.laoshengle.core.service.user.UserService;
import cn.laoshengle.user.impl.mapper.ResourcesMapper;
import cn.laoshengle.user.impl.mapper.RoleMapper;
import cn.laoshengle.user.impl.mapper.SystemUserMapper;
import cn.laoshengle.user.impl.pojo.RolePojo;
import cn.laoshengle.user.impl.pojo.SystemUserPojo;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private SystemUserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private ResourcesMapper resourcesMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SystemUserEntity getUserByLoginName(String loginName) {

        logger.info("[UserServiceImpl].[getUserByLoginName]------> loginName = {}", loginName);

        QueryWrapper<SystemUserPojo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName);

        SystemUserPojo systemUserPojo = userMapper.selectOne(queryWrapper);
        logger.info("[UserServiceImpl].[getUserByLoginName]------> systemUserPojo = {}", JSON.toJSONString(systemUserPojo));
        SystemUserEntity systemUserEntity = null;
        if (!StringUtils.isEmpty(systemUserPojo)) {
            systemUserEntity = new SystemUserEntity();
            BeanUtils.copyProperties(systemUserPojo, systemUserEntity);

            //获取该用户的角色列表
            logger.info("[UserServiceImpl].[getUserByLoginName]------> Select Role By User, userId = {}", systemUserEntity.getUserId());
            List<RolePojo> roles = roleMapper.selectByUserId(systemUserEntity.getUserId());
            if(CollectionUtils.isEmpty(roles)){
                //
            }
        }
        return systemUserEntity;
    }
}
