package com.zhxh.codeproj.designpattern.decorator2;

/**
 * Created by zhxh on 2019/4/13
 */
public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    //special method
    private void method2() {
        System.out.println("ConcreteDecorator2-->method2()");
    }

    @Override
    public void operate() {
        this.method2();
        super.operate();
    }
}
