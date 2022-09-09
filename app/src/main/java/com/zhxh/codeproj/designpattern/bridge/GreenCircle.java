package com.zhxh.codeproj.designpattern.bridge;

import com.zhxh.codeproj.Log;


public class GreenCircle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {
        Log.e("---", "Drawing Circle[ color: green, radius: "
                + radius + ", x: " + x + ", y: " + y + "]");
    }
}
