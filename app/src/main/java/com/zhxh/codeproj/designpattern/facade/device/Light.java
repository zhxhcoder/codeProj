package com.zhxh.codeproj.designpattern.facade.device;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/11/2.
 * 灯光
 */

public class Light {

    public void down() {
        Log.e("Light", "---将灯光调暗");
    }

    public void up() {
        Log.e("Light", "---将灯光调亮");
    }
}
