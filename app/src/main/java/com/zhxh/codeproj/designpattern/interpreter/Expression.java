package com.zhxh.codeproj.designpattern.interpreter;

/**
 * Created by zhxh on 2020-02-02.
 * 1. 创建一个表达式接口。
 */
public interface Expression {
    public boolean interpreter(String content);
}
