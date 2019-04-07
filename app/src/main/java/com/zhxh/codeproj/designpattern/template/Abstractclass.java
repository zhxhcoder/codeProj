package com.zhxh.codeproj.designpattern.template;

/**
 * Created by zhxh on 2019/4/7
 */
public abstract class Abstractclass {
    //base method
    protected abstract void doSomething();
    //base method
    protected abstract void doAnything();
    //template method
    public void templateMethod(){
        //invoke method, do somethings
        this.doAnything();
        this.doSomething();
    }
}
