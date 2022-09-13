package com.zhxh.codeproj.designpattern.template;

/**
 * Created by zhxh on 2019/4/7
 */
public class ConcreteClass2 extends AbstractClass {
    @Override
    protected void doSomething() {
        System.out.println("ConcreteClass2-->doSomething");
    }

    @Override
    protected void doAnything() {
        System.out.println("ConcreteClass2-->doAnything");
    }
}
