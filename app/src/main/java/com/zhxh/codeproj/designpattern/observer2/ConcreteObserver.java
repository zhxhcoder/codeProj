package com.zhxh.codeproj.designpattern.observer2;

/**
 * Created by zhxh on 2019/4/9
 */
public class ConcreteObserver implements Observer {
    String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        System.out.println(name + " ConcreteObserver-update()-msg: " + msg);
    }
}
