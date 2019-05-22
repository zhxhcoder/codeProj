package com.zhxh.codeproj.javatest.javatest6;

/**
 * Created by zhxh on 2019/05/22
 */
public class NodeBean {
    public int index;
    public String key;
    public String value;
    public NodeBean innerNode;

    public NodeBean(int index, String key, String value, NodeBean innerNode) {
        this.index = index;
        this.key = key;
        this.value = value;
        this.innerNode = innerNode;
    }

    public NodeBean(int index, String key, String value) {
        this.index = index;
        this.key = key;
        this.value = value;
    }

    public NodeBean() {

    }

    public String getBeanString() {

        if (innerNode == null) {
            return "index=" + index + " key=" + key + " value=" + value + " innerNode=" + "null";
        } else {
            return "index=" + index + " key=" + key + " value=" + value + " innerNode=" + innerNode.key + ":" + innerNode.value;
        }
    }
}
