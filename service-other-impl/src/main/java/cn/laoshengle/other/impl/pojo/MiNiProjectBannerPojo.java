package cn.laoshengle.other.impl.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description: 微信小程序Banner实体
 * @author: 龙逸
 * @createDate: 2020/05/23 15:39:50
 **/
@Data
@ToString
@TableName(value = "mini_project_banner")
public class MiNiProjectBannerPojo {
    /**
     * 系统ID
     */
    @TableId(value = "system_id")
    private String systemId;

    /**
     * Banner名称
     */
    private String bannerName;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * Banner的URL地址
     */
    private String bannerUrl;

    /**
     * 跳转路径
     */
    private String linkUrl;

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
