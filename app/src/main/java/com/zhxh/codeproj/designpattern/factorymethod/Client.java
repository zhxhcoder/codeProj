package com.zhxh.codeproj.designpattern.factorymethod;

/**
 * Created by zhxh on 2019/4/7
 */
public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        product.method2();

    }
}
