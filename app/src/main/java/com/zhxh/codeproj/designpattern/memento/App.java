package com.zhxh.codeproj.designpattern.memento;

import com.zhxh.codeproj.Log;

/*
 * 备忘录模式（Memento Pattern）保存一个对象的某个状态，以便在适当的时候恢复对象。备忘录模式属于行为型模式。
 * <p>
 * 主要解决：所谓备忘录模式就是在不破坏封装的前提下，捕获一个对象的内部状态，
 * 并在该对象之外保存这个状态，这样可以在以后将对象恢复到原先保存的状态。
 */
public class App {
    public static void main(String[] args) {
        // 管理者
        CareTaker careTaker = new CareTaker();

        Originator originator = new Originator();
        originator.setState("State #1");
        originator.setState("State #2");

        // 保存状态
        careTaker.add(originator.setSateToMemento());

        originator.setState("State #3");

        // 保存状态
        careTaker.add(originator.setSateToMemento());

        originator.setState("State #4");

        Log.e("---", "Current State: " + originator.getState());
        // 得到保存的状态
        String fromMemento1 = originator.getStateFromMemento(careTaker.get(0));
        Log.e("---", "First Saved State: " + fromMemento1);
        String fromMemento2 = originator.getStateFromMemento(careTaker.get(1));
        Log.e("---", "Second Saved State: " + fromMemento2);

        /*
         * /---: Current State: State #4
         * /---: First Saved State: State #2
         * /---: Second Saved State: State #3
         */
    }
}
