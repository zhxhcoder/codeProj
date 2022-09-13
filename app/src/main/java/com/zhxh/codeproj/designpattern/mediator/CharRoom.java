package com.zhxh.codeproj.designpattern.mediator;

import com.zhxh.codeproj.Log;

import java.util.Date;

/**
 * 1. 创建中介类。
 */
public class CharRoom {
    public static void showMessage(User user, String message) {
        Log.e("---", new Date().toString()
                + " [" + user.getName() + "] : " + message);
    }
}
