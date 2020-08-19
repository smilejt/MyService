package cn.laoshengle.core.entity.request.user;

import cn.laoshengle.core.entity.request.BasePageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @description: 用户查询参数对象
 * @author: 龙逸
 * @createDate: 2020/08/18 20:46:01
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryUserForm extends BasePageParam{
    private static final long serialVersionUID = -1699822627203451135L;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String username;
}
