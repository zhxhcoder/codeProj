package com.zhxh.codeproj.multithread.deadlock;

/*写一个两个线程死锁的程序
 * coded by zhxh
 */
class MyDeadLock implements Runnable {
    boolean flag;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public MyDeadLock(boolean flag) {
        this.flag = flag;
    }

    public void run() {
        if (this.flag) {
            synchronized (o1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("我没发生死锁");

                }
            }
        } else {
            synchronized (o2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("我也是");
                }
            }
        }
    }
}
