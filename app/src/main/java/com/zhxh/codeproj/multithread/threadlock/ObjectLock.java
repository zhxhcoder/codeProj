package com.zhxh.codeproj.multithread.threadlock;

/**
 * Created by zhxh on 2020/7/10
 */
public class ObjectLock {

    private Object lock = new Object();

    /**
     * 锁住非静态变量
     *
     * @throws InterruptedException
     */
    public void lockObjectField() throws InterruptedException {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10 * 1000);
        }
    }

    /**
     * 锁住 this 对象 this 就是当前对象实例
     *
     * @throws InterruptedException
     */
    public void lockThis() throws InterruptedException {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10 * 1000);
        }
    }

    /**
     * 直接锁住非静态方法
     *
     * @throws InterruptedException
     */
    public synchronized void methodLock() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(10 * 1000);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread worker = new Thread(new ObjectLockWorker());
            worker.setName("kite-" + i);
            worker.start();
        }
    }

    public static class ObjectLockWorker implements Runnable {
        @Override
        public void run() {
            try {
                ObjectLock objectLock = new ObjectLock();
                // 方式 1
                objectLock.lockObjectField();
                // 方式 2
                //objectLock.lockThis();
                // 方式 3
                //objectLock.methodLock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}