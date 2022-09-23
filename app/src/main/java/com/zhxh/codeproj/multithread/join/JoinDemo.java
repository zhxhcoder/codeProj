package com.zhxh.codeproj.multithread.join;

/*
coded by zhxh
 */
class JoinDemo implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("线程1第" + i + "次执行！");
        }
    }
}