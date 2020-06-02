package cn.laoshengle.local.controller;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/06/02 19:02:08
 **/
@Data
@ToString
public class TestNode {

    private Long id;
    private Long nextNode;
    private Long previousNode;
    private String name;
    private Long parentId;
    private List<TestNode> childList;
}
