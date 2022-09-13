package com.zhxh.codeproj.designpattern.visitor;

import com.zhxh.codeproj.designpattern.visitor.impl.Computer;
import com.zhxh.codeproj.designpattern.visitor.impl.Keyboard;
import com.zhxh.codeproj.designpattern.visitor.impl.Monitor;
import com.zhxh.codeproj.designpattern.visitor.impl.Mouse;
import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2020-02-04.
 * 4. 创建实现了 ComputerPartVisitor 的实体访问者。
 */
public class ComputerPartDisplayVisitor implements ComputerPartVisitor {

    @Override
    public void visit(Computer computer) {
        Log.e("---", "Displaying Computer.");
    }

    @Override
    public void visit(Mouse mouse) {
        Log.e("---", "Displaying Mouse.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        Log.e("---", "Displaying Keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        Log.e("---", "Displaying Monitor.");
    }
}
