package cn.laoshengle.core.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/23 15:45:59
 **/
@Data
@ToString
public class MiNiProjectBannerEntity implements Serializable {
    private static final long serialVersionUID = 7390509909533050890L;

    /**
     * 系统ID
     */
    private String systemId;

    /**
     * Banner名称
     */
    private String bannerName;

    /**
     * Banner的URL地址
     */
    private String bannerUrl;

    /**
     * 启动标识
     */
    private Integer enableMark;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 逻辑删除标识
     */
    private Integer deleteMark;
}
