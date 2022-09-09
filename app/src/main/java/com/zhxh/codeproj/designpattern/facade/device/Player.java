package com.zhxh.codeproj.designpattern.facade.device;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/11/2.
 * 播放器
 */

public class Player {

    public void on() {
        Log.e("Player", "---打开播放器");
    }

    public void off() {
        Log.e("Player", "---关闭播放器");
    }

    public void make3DListener() {
        Log.e("Player", "---将播放器音调设为环绕立体声 ");
    }
}
