package com.zhxh.codeproj.designpattern.observer2;

/**
 * Created by zhxh on 2019/4/9
 */
public class ConcreteObserver implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("got msg: " + msg);

    }
}
