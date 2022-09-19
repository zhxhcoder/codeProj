package com.zhxh.codeproj.architecture.libhandler;

/**
 * Created by zhxh on 2019/3/16
 */
public class Looper {
    public MessageQueue mQueue;
    //线程间数据隔离
    static ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    private Looper() {
        mQueue = new MessageQueue();
    }

    //创建looper
    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("一个线程只能有一个looper对象");
        }
        sThreadLocal.set(new Looper());
    }

    //返回当前线程的looper对象
    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    //取出消息
    public static void loop() {
        Looper self = myLooper();
        if (self == null) {
            throw new RuntimeException("当前线程没有Looper对象，请调用prepare()");
        }
        MessageQueue queue = self.mQueue;
        //循环取出msg
        for (; ; ) {
            Message msg = queue.next();
            if (msg != null) {
                msg.target.dispatchMessage(msg);
            }
        }
    }
}
