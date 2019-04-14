package com.zhxh.codeproj.designpattern.adapter;

/**
 * Created by zhxh on 2019/4/9
 * 场景类测试
 */
public class Client {
    public static void main(String[] args) {
        //原有的逻辑
        Target target1 = new ConcreteTarget();
        target1.request();
        //现在增加了适配器角色后的业务逻辑
        Target target2 = new Adapter();
        target2.request();
    }
}
