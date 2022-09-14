package com.zhxh.codeproj.designpattern.observer2;

/**
 * Created by zhxh on 2019/4/9
 */
public class ConcreteObservable extends Observable {
    public void doSomething(String msg) {
        System.out.println("ConcreteObservable-doSomething()-msg: " + msg);
        super.notifyObservers(msg);
    }
}

