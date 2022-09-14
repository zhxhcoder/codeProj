package com.zhxh.codeproj.designpattern.observer2;

/**
 * Created by zhxh on 2019/4/9
 * 观察者模式，也叫做发布订阅模式，定义对象间一种一对多的依赖关系，使得每当一个对象改变状态，
 * 则所有依赖于它的对象都会得到通知并被自动更新
 */
public class Client {
    public static void main(String[] args) {
        //创建一个被观察者
        ConcreteObservable observable = new ConcreteObservable();
        //创建一个观察者
        Observer observer1 = new ConcreteObserver("aa");
        Observer observer2 = new ConcreteObserver("bb");
        Observer observer3 = new ConcreteObserver("cc");
        //添加观察者
        observable.addObserver(observer1);
        observable.addObserver(observer2);
        observable.addObserver(observer3);
        observable.removeObserver(observer2);
        //发送信息
        observable.doSomething("haha");
    }
}
