package cn.laoshengle.user.impl.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author longjuntao
 * @description: 资源表
 * @date 2020/8/11 10:06
 */
@Data
@TableName("t_resources")
public class ResourcesPojo {
    /**
    * 资源ID(UUID)
    */
    @TableId(value = "resource_id")
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