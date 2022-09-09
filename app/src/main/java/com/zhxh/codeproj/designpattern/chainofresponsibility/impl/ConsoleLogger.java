package com.zhxh.codeproj.designpattern.chainofresponsibility.impl;

import com.zhxh.codeproj.Log;
import com.zhxh.codeproj.designpattern.chainofresponsibility.AbstractLogger;

/**
 * Created by zhxh on 2020-02-03.
 * 2. 创建扩展了该记录器类的实体类。
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        Log.e("---", "Console::Logger  " + message);
    }
}
