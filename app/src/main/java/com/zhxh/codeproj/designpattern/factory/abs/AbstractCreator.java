package com.zhxh.codeproj.designpattern.factory.abs;

/*
抽象工厂类
 */
public abstract class AbstractCreator {
    //创建A产品家族
    public abstract AbstractProductA createProductA();
    public abstract AbstractProductB createProductB();
}
