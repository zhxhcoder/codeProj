package com.zhxh.codeproj.designpattern.observer2;

/**
 * Created by zhxh on 2019/4/9
 */
public class ConcreteObservable extends Observable {
    //do something
    public void doSomething(String msg) {
        //todo something
        super.notifyObservers(msg);
    }
}

