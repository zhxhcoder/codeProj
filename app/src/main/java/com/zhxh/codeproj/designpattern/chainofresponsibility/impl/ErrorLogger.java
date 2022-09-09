package com.zhxh.codeproj.designpattern.chainofresponsibility.impl;

import com.zhxh.codeproj.Log;
import com.zhxh.codeproj.designpattern.chainofresponsibility.AbstractLogger;

/**
 * Created by zhxh on 2020-02-03.
 */
public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        Log.e("---", "Error::Logger  " + message);
    }
}
