package com.zhxh.codeproj.designpattern.factorymethod;

/**
 * Created by zhxh on 2019/4/7
 */
public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product1 = creator.createProduct(ConcreteProduct1.class);
        product1.method2();

        Product product2 = creator.createProduct(ConcreteProduct2.class);
        product2.method();
        product2.method2();
    }
}
