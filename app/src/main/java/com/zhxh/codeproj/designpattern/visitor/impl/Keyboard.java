package com.zhxh.codeproj.designpattern.visitor.impl;

import com.zhxh.codeproj.designpattern.visitor.ComputerPart;
import com.zhxh.codeproj.designpattern.visitor.ComputerPartVisitor;

/**
 * Created by zhxh on 2020-02-04.
 * 2. 创建扩展了上述类的实体类。
 */
public class Keyboard implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
