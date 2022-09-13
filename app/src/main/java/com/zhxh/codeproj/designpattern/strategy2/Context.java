package com.zhxh.codeproj.designpattern.strategy2;

/**
 * Created by zhxh on 2019/4/14
 */
public class Context {
    //abstract strategy
    private Strategy strategy;

    //special strategy
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    //method after
    public void doAnything() {
        this.strategy.doSomething();
    }
}
