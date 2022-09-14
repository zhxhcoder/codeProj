package com.zhxh.codeproj.designpattern.proxy2;

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

    public Proxy(Subject... objects) {
        this.subject = objects[0];
    }

    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }

    private void before() {
        System.out.println("Proxy-->before()");
    }

    private void after() {
        System.out.println("Proxy-->after()");
    }
}
