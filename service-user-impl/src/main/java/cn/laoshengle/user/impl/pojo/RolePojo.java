package cn.laoshengle.user.impl.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author longjuntao
 * @description: 角色表
 * @date 2020/8/11 10:06
 */
@Data
@TableName("t_role")
public class RolePojo {
    /**
     * 角色ID(UUID)
     */
    @TableId(value = "role_id")
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