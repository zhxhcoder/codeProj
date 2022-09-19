package com.zhxh.codeproj.architecture.libhandler;

/**
 * Created by zhxh on 2019/3/16
 */
public class Message {
    public Handler target;
    public Object obj;
    public int what;

    public Message() {
    }

    @Override
    public String toString() {
        return obj.toString();
    }
}
