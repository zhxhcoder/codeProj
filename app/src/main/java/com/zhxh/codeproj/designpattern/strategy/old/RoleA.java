package com.zhxh.codeproj.designpattern.strategy.old;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/10/30.
 */

public class RoleA extends Role {

    public RoleA(String name) {
        this.name = name;
    }

    @Override
    protected void display() {
        Log.e("", "样子1");
    }

    @Override
    protected void run() {
        Log.e("", "金蚕脱壳");
    }

    @Override
    protected void attack() {
        Log.e("", "降龙十八掌");
    }

    @Override
    protected void defend() {
        Log.e("", "铁头功");
    }
}
