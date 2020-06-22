package com.zhxh.codeproj.designpattern.bridge;

/**
 * Created by zhxh on 2019/4/14
 */
public class Client {
    public static void main(String[] args) {
        Implementor imp = new ConcreteImplementor1();
        Abstraction abs = new RefinedAbstraction(imp);
        abs.request();
    }
}
