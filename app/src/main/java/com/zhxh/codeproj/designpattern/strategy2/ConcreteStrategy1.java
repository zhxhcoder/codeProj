package com.zhxh.codeproj.designpattern.strategy2;

/**
 * Created by zhxh on 2019/4/14
 */
public class ConcreteStrategy1 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("ConcreteStrategy1-->doSomething()");
    }
}
