package com.zhxh.codeproj.architecture.libhandler;

/**
 * Created by zhxh on 2019/3/18
 *
 * Handler有两个作用
 * 1，处理Message，这是它作为"处理者"的本职所在
 * 2，将某个Message压入MessageQueue中。
 */
public class Handler {
    public MessageQueue queue;

    public Handler() {
        Looper mLooper = Looper.myLooper();
        queue = mLooper.mQueue;
    }

    /*
    将某个Message压入MessageQueue中
    或者post
     */
    public void sendMessage(Message msg) {
        msg.target = this;
        queue.enqueueMessage(msg);
    }

    //处理消息
    public void handleMessage(Message msg) {
    }

    //处理消息
    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }
}
