package com.zhxh.codeproj.designpattern.command2;

/**
 * Created by zhxh on 2019/4/15
 */
public class Client {
    public static void main(String[] args) {
        //声明调用者invoker
        Invoker invoker = new Invoker();
//        定义接收者
        Receiver receiver1 = new ConcreteReceiver1();
        Receiver receiver2 = new ConcreteReceiver2();
//        定义一个发送给接收者的命令
        Command command1 = new ConcreteCommand1(receiver1);
        Command command2 = new ConcreteCommand2(receiver2);
        Command command3 = new ConcreteCommand1(receiver2);
//        把命令交给调用者去执行
        invoker.setCommand(command1);
        invoker.action();
        invoker.setCommand(command2);
        invoker.action();
        invoker.setCommand(command3);
        invoker.action();
    }
}
