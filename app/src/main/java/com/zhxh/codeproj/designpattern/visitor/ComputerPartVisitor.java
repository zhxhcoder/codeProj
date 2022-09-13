package com.zhxh.codeproj.designpattern.visitor;

import com.zhxh.codeproj.designpattern.visitor.impl.Computer;
import com.zhxh.codeproj.designpattern.visitor.impl.Keyboard;
import com.zhxh.codeproj.designpattern.visitor.impl.Monitor;
import com.zhxh.codeproj.designpattern.visitor.impl.Mouse;

/**
 * Created by zhxh on 2020-02-04.
 * 3. 定义一个表示访问者的接口。
 */
public interface ComputerPartVisitor {

    public void visit(Computer computer);

    public void visit(Mouse mouse);

    public void visit(Keyboard keyboard);

    public void visit(Monitor monitor);
}
