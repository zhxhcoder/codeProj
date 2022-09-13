package com.zhxh.codeproj.designpattern.factory.abs;

/*
产品等级1的实现类
 */
public class Creator2 extends AbstractCreator {

    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
