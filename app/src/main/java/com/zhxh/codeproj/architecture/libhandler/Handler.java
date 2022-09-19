package com.zhxh.codeproj.architecture.libhandler;

/**
 * Created by zhxh on 2019/3/18
 */
public class Handler {
    public MessageQueue queue;

    public Handler() {
        Looper mLooper = Looper.myLooper();
        queue = mLooper.mQueue;
    }

    public void sendMessage(Message msg) {
        msg.target = this;
        queue.enqueueMessage(msg);
    }

    //处理消息
    public void handleMessage(Message msg) {
    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }
}
