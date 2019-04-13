package com.zhxh.codeproj.designpattern.decorator;

/**
 * Created by zhxh on 2019/4/13
 */
public class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
    }
    //special method
    private void method1(){
        System.out.println("method1 decorator");
    }

    @Override
    public void operate() {
        this.method1();
        super.operate();
    }
}
