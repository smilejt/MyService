package cn.laoshengle.user.impl.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author longjuntao
 * @description: 用户对象
 * @date 2020/8/10 10:06
 */
@Data
@TableName("t_system_user")
public class SystemUserPojo {
    /**
     * 用户ID(UUID)
     */
    @TableId(value = "user_id")
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
}

