package com.zhxh.codeproj.designpattern.factory.simple;

/*
 * Created by zhxh on 2019/4/7
 * 相对于工厂方法类，去掉了继承抽象类
 * 今天继续设计模式之旅，给大家带来工厂模式，简单列一下这个模式的家族：
 * 1、静态工厂模式
 * 2、简单工厂模式
 * 3、工厂方法模式
 * 4、抽象工厂模式
 * <p>
 * <p>
 * 1、静态工厂模式
 */
public class Client {
    public static void main(String[] args) {
        Product product1 = Creator.createProduct(ConcreteProduct1.class);
        product1.absMethod();

        Product product2 = Creator.createProduct(ConcreteProduct2.class);
        product2.method();
        product2.absMethod();
    }
}
