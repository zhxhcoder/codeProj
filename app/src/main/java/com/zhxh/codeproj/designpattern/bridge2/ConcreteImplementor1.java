package com.zhxh.codeproj.designpattern.bridge2;

/**
 * Created by zhxh on 2019/4/14
 */
public class ConcreteImplementor1 implements Implementor {
    @Override
    public void doSomething() {
        System.out.println("do some 1");
    }

    @Override
    public void doAnything() {
        System.out.println("do any 2");

    }
}
