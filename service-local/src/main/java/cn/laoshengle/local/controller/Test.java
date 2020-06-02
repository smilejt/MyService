package cn.laoshengle.local.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @description:
 * @author: 龙逸
 * @createDate: 2020/06/02 19:05:10
 **/
public class Test {
    public static void main(String[] args) {

        TestNode topNode = null;

        List<NodePojo> list = new ArrayList<>();
        //模拟一级节点
        list.add(new NodePojo(1L, null, 2L, "测试节点01", null));
        list.add(new NodePojo(2L, 1L, 3L, "测试节点02", null));
        list.add(new NodePojo(3L, 2L, null, "测试节点03", null));
        //模拟二级节点
        list.add(new NodePojo(4L, null, 5L, "测试节点04", 1L));
        list.add(new NodePojo(5L, 4L, 6L, "测试节点05", 1L));
        list.add(new NodePojo(6L, 5L, 7L, "测试节点06", 1L));
        list.add(new NodePojo(7L, 6L, null, "测试节点07", 1L));
        //模拟三级节点
        list.add(new NodePojo(8L, null, 9L, "测试节点08", 6L));
        list.add(new NodePojo(9L, 8L, null, "测试节点09", 6L));
        //模拟四级节点
        list.add(new NodePojo(10L, null, null, "测试节点10", 8L));


        list.add(new NodePojo(11L, null, 12L, "测试节点11", 3L));
        list.add(new NodePojo(12L, 11L, null, "测试节点12", 3L));
        list.add(new NodePojo(13L, null, null, "测试节点13", 11L));


        Map<Long, TestNode> tempMap = new HashMap<>();
        List<TestNode> tempList = new ArrayList<>();
        for (NodePojo pojo : list) {
            TestNode tempNode = new TestNode();
            BeanUtils.copyProperties(pojo, tempNode);
            if (null == tempNode.getParentId() && null == tempNode.getPreviousNode()) {
                topNode = tempNode;
            }
            tempMap.put(tempNode.getId(), tempNode);
            tempList.add(tempNode);
        }

        List<TestNode> resultList = new ArrayList<>();

        //获取所有的一级节点
        if (null != topNode) {
            getNextNode(topNode, resultList, tempMap);
        }


        for (TestNode testNode : resultList) {
            recursiveTree(testNode, tempList, tempMap);
        }

        System.out.println("组装完成的树:" + JSON.toJSONString(resultList));
    }

    /**
     * @param topNode    顶层节点
     * @param resultList 当前级别组装完成的List
     * @param tempMap    所有节点的Map对象
     */
    private static void getNextNode(TestNode topNode, List<TestNode> resultList, Map<Long, TestNode> tempMap) {
        resultList.add(topNode);
        if (null != topNode.getNextNode()) {
            getNextNode(tempMap.get(topNode.getNextNode()), resultList, tempMap);
        }
    }

    public static void recursiveTree(TestNode parent, List<TestNode> list, Map<Long, TestNode> tempMap) {
        for (TestNode node : list) {
            if (Objects.equals(parent.getId(), node.getParentId())) {
                recursiveTree(node, list, tempMap);
                if (null == node.getPreviousNode()) {
                    List<TestNode> childList = new ArrayList<>();
                    getNextNode(node, childList, tempMap);
                    parent.setChildList(childList);
                }
            }
        }
    }
}
