package cn.laoshengle.core.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author longjuntao
 * @description: 角色表
 * @date 2020/8/11 11:50
 */
@Data
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 8752616095711953524L;
    /**
     * 角色ID(UUID)
     */
    private String roleId;

    /**
     * 角色编码(如:admin)
     */
    private String roleCode;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 版本
     */
    private Integer version;
}
