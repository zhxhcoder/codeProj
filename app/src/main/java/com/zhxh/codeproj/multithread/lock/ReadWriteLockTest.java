package com.zhxh.codeproj.multithread.lock;

import java.util.Random;
import java.util.concurrent.locks.*;

/*
Java中的Lock接口，比起synchronized，优势在哪里？

如果需要实现一个高效的缓存，它允许多个用户读，但只允许一个用户写，以此来保持它的完整性，如何实现？
Lock接口最大的优势是为读和写分别提供了锁。
读写锁ReadWriteLock拥有更加强大的功能，它可细分为读锁和解锁。
读锁可以允许多个进行读操作的线程同时进入，但不允许写进程进入；写锁只允许一个写进程进入，在这期间任何进程都不能再进入。
（完全符合题目中允许多个用户读和一个用户写的条件） 要注意的是每个读写锁都有挂锁和解锁，
最好将每一对挂锁和解锁操作都用try、finally来套入中间的代码，这样就会防止因异常的发生而造成死锁得情况。
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final TheData myData = new TheData();  //这是各线程的共享数据
        for (int i = 0; i < 3; i++) { //开启3个读线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        myData.get();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) { //开启3个写线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        myData.put(new Random().nextInt(10000));
                    }
                }
            }).start();
        }
    }
}

