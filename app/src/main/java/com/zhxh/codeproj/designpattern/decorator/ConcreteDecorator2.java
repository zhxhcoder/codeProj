package com.zhxh.codeproj.designpattern.decorator;

/**
 * Created by zhxh on 2019/4/13
 */
public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }
    //special method
    private void method2(){
        System.out.println("method2 decorator");
    }

    @Override
    public void operate() {
        this.method2();
        super.operate();
    }
}
