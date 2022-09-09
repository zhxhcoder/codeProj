package com.zhxh.codeproj.designpattern.adapter2;

/**
 * Created by zhxh on 2019/4/9
 * 目标角色的实现类
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("hahaha");
    }
}
