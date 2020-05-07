package cn.laoshengle.core.entity.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/05/07 17:36:45
 **/
@Data
@ToString
public class ListEntity implements Serializable {
    private static final long serialVersionUID = -3003086462002535793L;

    List<?> dataList;
}
