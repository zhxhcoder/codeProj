package com.zhxh.codeproj.designpattern.state2;

/**
 * Created by zhxh on 2019/4/14
 */
public abstract class State {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void handle1();

    public abstract void handle2();
}
