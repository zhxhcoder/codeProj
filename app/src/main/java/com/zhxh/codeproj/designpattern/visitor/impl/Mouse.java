package com.zhxh.codeproj.designpattern.visitor.impl;

import com.zhxh.codeproj.designpattern.visitor.ComputerPart;
import com.zhxh.codeproj.designpattern.visitor.ComputerPartVisitor;

/**
 * Created by zhxh on 2020-02-04.
 */
public class Mouse implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
