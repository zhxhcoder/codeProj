package com.zhxh.codeproj.designpattern.command2;

/**
 * Created by zhxh on 2019/4/15
 */
public class Client {
    public static void main(String[] args) {
        //声明调用者invoker
        Invoker invoker = new Invoker();
//        定义接收者
        Receiver receiver = new ConcreteReceiver1();
//        定义一个发送给接收者的命令
        Command command = new ConcreteCommand1(receiver);
//        把命令交给调用者去执行
        invoker.setCommand(command);
        invoker.action();
    }
}
