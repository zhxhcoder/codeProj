package com.zhxh.codeproj.designpattern.command;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/10/31.
 * 门
 */

public class Door {

    public void open() {
        Log.e("Door:", "---打开门");
    }

    public void close() {
        Log.e("Door:", "---关闭门");
    }
}
