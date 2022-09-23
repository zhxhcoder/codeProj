package com.zhxh.codeproj.multithread.deadlock;

/*
coded by zhxh
 */
public class DeadLockTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread t1 = new Thread(new MyDeadLock(true));
        Thread t2 = new Thread(new MyDeadLock(false));
        t1.start();
        t2.start();

    }
}
