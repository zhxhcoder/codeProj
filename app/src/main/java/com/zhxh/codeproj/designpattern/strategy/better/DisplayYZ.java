package com.zhxh.codeproj.designpattern.strategy.better;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/10/30.
 */

public class DisplayYZ implements IDisplayBehavior {

    @Override
    public void display() {
        Log.e("---", "样子2");
    }
}
