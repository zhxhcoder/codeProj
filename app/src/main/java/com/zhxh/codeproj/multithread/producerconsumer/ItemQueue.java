package com.zhxh.codeproj.multithread.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/*
  作为 {@link Producer}-{@link Consumer} 交流的通道。
 */
public class ItemQueue {
    private BlockingQueue<Item> queue;

    public ItemQueue() {
        queue = new LinkedBlockingQueue<>(5);
    }

    public void put(Item item) throws InterruptedException {
        queue.put(item);
    }

    public Item take() throws InterruptedException {
        return queue.take();
    }
}
