package com.zhxh.codeproj.designpattern.templatemethod2;

/**
 * Created by zhxh on 2019/4/7
 */
public abstract class AbstractClass {
    //基本方法
    protected abstract void doSomething();

    //基本方法
    protected abstract void doAnything();

    //模板方法
    public void templateMethod() {
        //调用方法，做一些事情
        this.doAnything();
        this.doSomething();
    }
}
