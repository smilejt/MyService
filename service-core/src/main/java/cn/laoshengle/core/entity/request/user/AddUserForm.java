package cn.laoshengle.core.entity.request.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 新增用户实体对象
 * @author: 龙逸
 * @createDate: 2020/08/18 18:15:18
 **/
@Data
public class AddUserForm implements Serializable {
    private static final long serialVersionUID = -7898558630968078264L;

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
     * 创建时间
     */
    private Date creatTime;
}
