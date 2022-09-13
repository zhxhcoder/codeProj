package com.zhxh.codeproj.designpattern.state2;

/**
 * Created by zhxh on 2019/4/14
 */
class ConcreteState2 extends State {
    @Override
    public void handle1() {
        super.context.setCurrentState(Context.STATE1);
        super.context.handle1();
    }

    @Override
    public void handle2() {

        System.out.println("state2 do");
    }
}
