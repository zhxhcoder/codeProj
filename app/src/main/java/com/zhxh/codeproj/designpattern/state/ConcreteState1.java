package com.zhxh.codeproj.designpattern.state;

/**
 * Created by zhxh on 2019/4/14
 */
class ConcreteState1 extends State {
    @Override
    public void handle1() {
        //本状态逻辑
        System.out.println("state1 do");
    }

    @Override
    public void handle2() {

        super.context.setCurrentState(Context.STATE2);
        super.context.handle2();
    }
}
