package com.zhxh.codeproj.designpattern.observer2;

import java.util.Vector;

/**
 * Created by zhxh on 2019/4/9
 */
public abstract class Observable {

    //define array for observer
    private Vector<Observer> observers = new Vector<>();

    //add a observer
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    //remove a observer
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    public void notifyObservers(String msg) {
        for (Observer observer : this.observers) {
            observer.update(msg);
        }
    }
}
