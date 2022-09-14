package com.zhxh.codeproj.designpattern.proxy2;

/**
 * Created by zhxh on 2019/4/9
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject-->request()");
    }
}
