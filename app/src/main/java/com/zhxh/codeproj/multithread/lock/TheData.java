package com.zhxh.codeproj.multithread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class TheData {
    private Object data = null;
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public void get() {
        rwl.readLock().lock();  //读锁开启，读线程均可进入
        try { //用try finally来防止因异常而造成的死锁
            System.out.println(Thread.currentThread().getName() + " 正准备读");
            Thread.sleep(new Random().nextInt(100));
            System.out.println(Thread.currentThread().getName() + " 已经读取到数据 " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock(); //读锁解锁
        }
    }

    public void put(Object data) {
        rwl.writeLock().lock();  //写锁开启，这时只有一个写线程进入
        try {
            System.out.println(Thread.currentThread().getName() + " 正准备读");
            Thread.sleep(new Random().nextInt(100));
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " 已经读取到数据 " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock(); //写锁解锁
        }
    }
}