package com.zhxh.codeproj.designpattern.visitor;

import com.zhxh.codeproj.designpattern.visitor.impl.Computer;

/*
 * 在访问者模式（Visitor Pattern）中，我们使用了一个访问者类，它改变了元素类的执行算法。
 * 通过这种方式，元素的执行算法可以随着访问者改变而改变。
 * 这种类型的设计模式属于行为型模式。根据模式，元素对象已接受访问者对象，这样访问者对象就可以处理元素对象上的操作。
 * <p>
 * 主要解决：稳定的数据结构和易变的操作耦合问题。
 */
public class App {
    public static void main(String[] args) {
        //5. 使用 ComputerPartDisplayVisitor 来显示 Computer 的组成部分。

        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
        /*
         *打印：
         *---: Displaying Mouse.
         *---: Displaying Keyboard.
         *---: Displaying Monitor.
         *---: Displaying Computer.
         */
    }
}
