package cn.laoshengle.core.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author longjuntao
 * @description: 资源表
 * @date 2020/8/11 11:54
 */
@Data
public class ResourcesEntity implements Serializable {

    private static final long serialVersionUID = 1907326381509275159L;

    private String resourceId;

    /**
     * 资源代码
     */
    private String resourceCode;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 资源URL
     */
    private String resourceUrl;

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
