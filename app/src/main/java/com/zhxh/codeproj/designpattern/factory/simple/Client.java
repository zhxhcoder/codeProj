package com.zhxh.codeproj.designpattern.factory.simple;

/**
 * Created by zhxh on 2019/4/7
 * <p>
 * 相对于工厂方法类，去掉了继承抽象类
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
