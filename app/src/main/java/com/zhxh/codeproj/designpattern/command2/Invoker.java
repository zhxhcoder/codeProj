package com.zhxh.codeproj.designpattern.command2;

/**
 * Created by zhxh on 2019/4/15
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        this.command.execute();
    }
}
