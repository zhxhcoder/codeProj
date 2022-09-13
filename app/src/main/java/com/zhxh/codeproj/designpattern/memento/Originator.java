package com.zhxh.codeproj.designpattern.memento;

/**
 * Created by zhxh on 2020-02-02.
 * 2. 创建 Originator 类。发件人/发起人
 */
public class Originator {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento setSateToMemento() {
        return new Memento(state);
    }

    public String getStateFromMemento(Memento memento) {
        return memento.getState();
    }
}
