package cn.laoshengle.flowable.pojo;

import lombok.Data;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/06/16 21:04:25
 **/
@Data
public class TaskRepresentation {

    public TaskRepresentation(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TaskRepresentation() {
    }


    private String id;
    private String name;
}
