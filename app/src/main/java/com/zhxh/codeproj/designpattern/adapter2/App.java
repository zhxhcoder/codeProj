package com.zhxh.codeproj.designpattern.adapter2;

/**
 * Created by zhxh on 2019/4/9
 * 场景类测试
 * 适配器模式:
 * 定义：将一个类的接口转换成客户期望的另一个接口，适配器让原本接口不兼容的类可以相互合作。
 * 这个定义还好，说适配器的功能就是把一个接口转成另一个接口。
 */
public class App {
    public static void main(String[] args) {
        //原有的逻辑
        Target target1 = new ConcreteTarget();
        target1.request();
        //现在增加了适配器角色后的业务逻辑
        Target target2 = new Adapter();
        target2.request();
    }
}
