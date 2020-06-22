package com.zhxh.codeproj.designpattern.command;

/**
 * Created by zhxh on 2019/4/15
 */
public class ConcreteCommand1 extends Command {
    private Receiver receiver;

    public ConcreteCommand1(Receiver receiver) {
        this.receiver = receiver;

    }

    @Override
    public void execute() {
        this.receiver.doSomething();

    }
}
