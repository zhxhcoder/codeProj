package com.zhxh.codeproj.designpattern.proxy;

/**
 * Created by zhxh on 2019/4/9
 */
public class Proxy implements Subject {
    //proxy for some class
    private Subject subject = null;

    //default proxy
    public Proxy() {
        this.subject = new Proxy();
    }

    public Proxy(Object... objects) {

    }

    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }

    private void before() {
        //todo something
    }

    private void after() {
        //todo something
    }
}
