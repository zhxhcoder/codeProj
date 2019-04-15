package com.zhxh.codeproj.designpattern.command;

/**
 * Created by zhxh on 2019/4/15
 */
public class ConcreteReceiver1 extends Receiver {
    @Override
    public void doSomething() {
        System.out.println("concrete receiver1");
    }
}
