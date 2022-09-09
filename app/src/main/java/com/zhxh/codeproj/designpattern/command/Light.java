package com.zhxh.codeproj.designpattern.command;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/10/31.
 * 电灯
 */

public class Light {

    public void on() {
        Log.e("Light:", "---打开灯");
    }

    public void off() {
        Log.e("Light:", "---关闭灯");
    }


}
