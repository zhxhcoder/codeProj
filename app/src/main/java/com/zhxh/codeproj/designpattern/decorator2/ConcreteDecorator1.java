package com.zhxh.codeproj.designpattern.decorator2;

/**
 * Created by zhxh on 2019/4/13
 */
public class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    //special method
    private void method1() {
        System.out.println("ConcreteDecorator1-->method1()");
    }

    @Override
    public void operate() {
        this.method1();
        super.operate();
    }
}
