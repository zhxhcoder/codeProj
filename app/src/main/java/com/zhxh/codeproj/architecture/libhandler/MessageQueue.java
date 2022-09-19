package com.zhxh.codeproj.architecture.libhandler;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhxh on 2019/3/18
 */
public class MessageQueue {
    private Message[] items;
    private int putIndex;
    private int takeIndex;

    private int count;
    private Lock lock;

    private Condition noEmpty;
    private Condition notFull;

    public MessageQueue() {
        items = new Message[50];
        lock = new ReentrantLock();
        noEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void enqueueMessage(Message msg) {
        //消息队列满了，等待消费
        try {
            lock.lock();
            while (count == 50) {
                notFull.await();
            }

            items[putIndex] = msg;
            putIndex = (++putIndex == items.length) ? 0 : putIndex;
            count++;
            //已经消费了，继续生产
            noEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    //出队
    public Message next() {
        Message msg = null;
        try {
            lock.lock();
            while (count == 0) {
                noEmpty.await();
            }

            msg = items[takeIndex];
            items[takeIndex] = null;
            takeIndex = (++takeIndex == items.length) ? 0 : takeIndex;
            count--;
            //已经消费了，继续生产
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return msg;
    }
}
