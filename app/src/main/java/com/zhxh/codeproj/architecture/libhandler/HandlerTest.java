package com.zhxh.codeproj.architecture.libhandler;

import java.util.UUID;

/**
 * Created by zhxh on 2019/3/19
 */
public class HandlerTest {
    public static void main(String[] args) {
        Looper.prepare();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println("接收=" + Thread.currentThread().getName() + "-" + msg.toString());

            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        Message msg = new Message();
                        synchronized (UUID.class) {
                            msg.what = 1;
                            msg.obj = UUID.randomUUID().toString();
                        }

                        System.out.println("发送=" + Thread.currentThread().getName() + "-" + msg.toString());
                        handler.sendMessage(msg);
                    }
                }
            }.start();
        }
        Looper.loop();
    }
}
