package com.zhxh.codeproj.designpattern.facade.device;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/11/2.
 * 爆米花机
 */

public class PopcornPopper {

    public void on() {
        Log.e("PopcornPopper", "---打开爆米花机");
    }

    public void off() {
        Log.e("PopcornPopper", "---关闭爆米花机");
    }

    public void makePopcorn() {
        Log.e("PopcornPopper", "---制作爆米花");
    }
}
