package cn.laoshengle.core.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author longjuntao
 * @description: 用户表
 * @date 2020/8/10 14:26
 */
@Data
public class SystemUserEntity implements Serializable {
    private static final long serialVersionUID = 4634331089230697204L;

    private String userId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户状态(1-正常,99-删除)
     */
    private Integer userStatus;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 上次更新时间
     */
    private Date lastUpdateTime;

    /**
     * 上次更新人
     */
    private String lastUpdatePerson;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 角色列表
     */
    private List<RoleEntity> roleList;

    /**
     * 资源列表
     */
    private List<ResourcesEntity> resourcesList;
}
