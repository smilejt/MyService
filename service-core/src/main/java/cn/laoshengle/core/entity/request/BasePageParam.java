package cn.laoshengle.core.entity.request;

import cn.laoshengle.core.constant.CommonConstant;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/08/18 21:21:58
 **/
@Data
public class BasePageParam implements Serializable {

    private static final long serialVersionUID = -8577947502673373071L;
    /**
     * 页码
     */
    private int current;

    /**
     * 条数
     */
    private int size;

    public int getCurrent() {
        return StringUtils.isEmpty(this.current) || this.current == CommonConstant.NUMBER_ZERO ? CommonConstant.PAGE_INDEX : this.current;
    }

    public int getSize() {
        return StringUtils.isEmpty(this.size) || this.size == CommonConstant.NUMBER_ZERO ? CommonConstant.PAGE_SIZE : this.size;
    }
}
