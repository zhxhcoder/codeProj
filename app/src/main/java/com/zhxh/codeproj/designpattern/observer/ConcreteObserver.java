package com.zhxh.codeproj.designpattern.observer;

/**
 * Created by zhxh on 2019/4/9
 */
public class ConcreteObserver implements Observer {
    public void update() {
        System.out.println("got msg, doing");
    }
}
