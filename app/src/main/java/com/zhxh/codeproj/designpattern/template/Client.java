package com.zhxh.codeproj.designpattern.template;

/**
 * Created by zhxh on 2019/4/7
 */
public class Client {
    public static void main(String[] args){
        AbstractClass class1=new ConcreteClass1();
        AbstractClass class2=new ConcreteClass2();

        //invoke template method
        class1.templateMethod();
        class2.templateMethod();
    }
}
