package com.zhxh.codeproj.designpattern.adapter;

/**
 * Created by zhxh on 2019/4/9
 * 适配器角色
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.doSomething();
    }
}
