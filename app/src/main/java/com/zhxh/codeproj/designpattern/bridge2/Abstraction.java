package com.zhxh.codeproj.designpattern.bridge2;

/**
 * Created by zhxh on 2019/4/14
 */
public abstract class Abstraction {
    private Implementor imp;

    public Abstraction(Implementor imp) {
        this.imp = imp;
    }

    public void request() {
        this.imp.doSomething();
    }

    public Implementor getImp() {
        return imp;
    }
}
