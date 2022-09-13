package com.zhxh.codeproj.designpattern.templatemethod2;

/**
 * Created by zhxh on 2019/4/7
 */
public class ConcreteClass1 extends AbstractClass {
    @Override
    protected void doSomething() {
        System.out.println("ConcreteClass1-->doSomething");
    }

    @Override
    protected void doAnything() {
        System.out.println("ConcreteClass1-->doAnything");
    }
}
