package com.zhxh.codeproj.designpattern.factory.method;

/**
 * Created by zhxh on 2019/4/7
 */
public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product1 = creator.createProduct(ConcreteProduct1.class);
        product1.absMethod();

        Product product2 = creator.createProduct(ConcreteProduct2.class);
        product2.method();
        product2.absMethod();
    }
}
