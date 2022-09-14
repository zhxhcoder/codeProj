package com.zhxh.codeproj.designpattern.observer2;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by zhxh on 2019/4/9
 */
public abstract class Observable {
    //监听队列
    private Deque<Observer> observers = new LinkedList<>();

    //添加监听者
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    //删除监听者
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    public void notifyObservers(String msg) {
        for (Observer observer : this.observers) {
            observer.update(msg);
        }
    }
}
