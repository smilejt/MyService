package cn.laoshengle.user.impl.service;

import cn.laoshengle.core.constant.CommonConstant;
import cn.laoshengle.core.entity.user.ResourcesEntity;
import cn.laoshengle.core.entity.user.RoleEntity;
import cn.laoshengle.core.entity.user.SystemUserEntity;
import cn.laoshengle.core.service.user.UserService;
import cn.laoshengle.user.impl.mapper.ResourcesMapper;
import cn.laoshengle.user.impl.mapper.RoleMapper;
import cn.laoshengle.user.impl.mapper.SystemUserMapper;
import cn.laoshengle.user.impl.pojo.ResourcesPojo;
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
import java.util.ArrayList;
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
    public SystemUserEntity getUserByLoginName(String loginName) {

        logger.info("[UserServiceImpl].[getUserByLoginName]------> loginName = {}", loginName);

        QueryWrapper<SystemUserPojo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName);
        queryWrapper.eq("user_status", CommonConstant.USER_STATUS_ENABLE);

        SystemUserPojo systemUserPojo = userMapper.selectOne(queryWrapper);
        logger.info("[UserServiceImpl].[getUserByLoginName]------> systemUserPojo = {}", JSON.toJSONString(systemUserPojo));
        SystemUserEntity systemUserEntity = null;
        if (!StringUtils.isEmpty(systemUserPojo)) {
            systemUserEntity = new SystemUserEntity();
            BeanUtils.copyProperties(systemUserPojo, systemUserEntity);

            //获取该用户的角色列表
            logger.info("[UserServiceImpl].[getUserByLoginName]------> Select Role By User, userId = {}", systemUserEntity.getUserId());
            List<RolePojo> roles = roleMapper.selectByUserId(systemUserEntity.getUserId());
            if (!CollectionUtils.isEmpty(roles)) {
                List<RoleEntity> roleList = new ArrayList<>();
                List<String> ids = new ArrayList<>();
                roles.forEach(pojo -> {
                    RoleEntity entity = new RoleEntity();
                    BeanUtils.copyProperties(pojo, entity);
                    roleList.add(entity);
                    ids.add(pojo.getRoleId());
                });

                systemUserEntity.setRoleList(roleList);

                //角色列表不为空,获取资源列表
                if (!CollectionUtils.isEmpty(ids)) {
                    List<ResourcesPojo> resources = resourcesMapper.selectByUserId(ids);
                    if (!CollectionUtils.isEmpty(resources)) {
                        List<ResourcesEntity> resourcesEntities = new ArrayList<>();
                        resources.forEach(res -> {
                            ResourcesEntity entity = new ResourcesEntity();
                            BeanUtils.copyProperties(res, entity);
                            resourcesEntities.add(entity);
                        });
                        systemUserEntity.setResourcesList(resourcesEntities);
                    }
                }
            }
        }
        return systemUserEntity;
    }
}
