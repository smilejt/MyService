package cn.laoshengle.core.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/24 15:05:36
 **/
@Data
@ToString
public class MiNiProjectMenuEntity implements Serializable {
    private static final long serialVersionUID = 600273579819201018L;
    /**
     * 系统ID
     */
    private String systemId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单图标URL
     */
    private String menuIconUrl;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 跳转路径
     */
    private String linkUrl;

    /**
     * 启用标识:0-禁用，1-启用
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
