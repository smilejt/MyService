package cn.laoshengle.other.impl.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description: 微信小程序菜单实体
 * @author: 龙逸
 * @createDate: 2020/05/24 14:59:54
 **/
@Data
@ToString
@TableName(value = "mini_project_menu")
public class MiNiProjectMenuPojo {

    /**
     * 系统ID
     */
    @TableId(value = "system_id")
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
