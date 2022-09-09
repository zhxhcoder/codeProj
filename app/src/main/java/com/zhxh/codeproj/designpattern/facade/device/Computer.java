package com.zhxh.codeproj.designpattern.facade.device;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/11/2.
 */

public class Computer {

    public void on() {
        Log.e("Computer", "---打开电脑");
    }

    public void off() {
        Log.e("Computer", "---关闭电脑");
    }
}
