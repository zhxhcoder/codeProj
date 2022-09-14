package com.zhxh.codeproj.designpattern.command2;

/**
 * Created by zhxh on 2019/4/15
 */
public class ConcreteReceiver1 extends Receiver {
    @Override
    public void doSomething(String msg) {
        System.out.println("ConcreteReceiver1--》doSomething-->" + msg);
    }
}
