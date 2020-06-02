package cn.laoshengle.local.controller;

import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/06/02 19:15:23
 **/
@Data
@ToString
public class NodePojo {

    public NodePojo() {
    }

    public NodePojo(Long id, Long previousNode, Long nextNode, String name, Long parentId) {
        this.id = id;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
        this.name = name;
        this.parentId = parentId;
    }

    private Long id;
    private Long nextNode;
    private Long previousNode;
    private String name;
    private Long parentId;
}
