package com.zhxh.codeproj.designpattern.facade;

/**
 * Created by zhxh on 2019/4/14
 */
public class Facade {
    private ClassA classA = new ClassA();
    private ClassB classB = new ClassB();

    public void methdoA() {
        this.classA.doSomethingA();
    }

    public void methodB() {
        this.classB.doSomethingB();
    }
}
