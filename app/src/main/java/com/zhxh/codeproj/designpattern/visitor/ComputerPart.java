package com.zhxh.codeproj.designpattern.visitor;

/**
 * Created by zhxh on 2020-02-04.
 * 1. 定义一个表示元素的接口。
 */
public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
