package com.zhxh.codeproj.designpattern.command2;

/**
 * Created by zhxh on 2019/4/15
 */
public class ConcreteReceiver2 extends Receiver {
    @Override
    public void doSomething() {
        System.out.println("ConcreteReceiver2--》doSomething");
    }
}
