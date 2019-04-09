package com.zhxh.codeproj.designpattern.observer;

/**
 * Created by zhxh on 2019/4/9
 */
public class Client {
    public static void main(String[] args){
        //create a observable
        ConcreteObservable observable=new ConcreteObservable();
        //create a observer
        Observer observer=new ConcreteObserver();
        //observer
        observable.addObserver(observer);
        //do something
        observable.doSomething("haha");
    }
}
