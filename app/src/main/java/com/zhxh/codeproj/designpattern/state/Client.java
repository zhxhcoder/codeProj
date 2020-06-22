package com.zhxh.codeproj.designpattern.state;

/**
 * Created by zhxh on 2019/4/14
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setCurrentState(new ConcreteState1());
        context.handle1();
        context.handle2();
    }
}
