package com.zhxh.codeproj.designpattern.factory.simple;

/**
 * Created by zhxh on 2019/4/7
 */
public abstract class Product {
    public void method() {
        //业务逻辑处理
        System.out.println("Product-->method");
    }

    //抽象方法
    public abstract void absMethod();
}
