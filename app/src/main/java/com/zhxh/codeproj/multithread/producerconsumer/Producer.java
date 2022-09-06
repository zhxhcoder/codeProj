package com.zhxh.codeproj.multithread.producerconsumer;

import java.util.Random;

/*
 负责产生可以表示为{@link Item}并提交到队列的工作单元 的类
 */
public class Producer {
    private final ItemQueue queue;
    private final String name;
    private int itemId;

    public Producer(String name, ItemQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    /*
     将项目放入队列
     */
    public void produce() throws InterruptedException {
        Item item = new Item(name, itemId++);
        queue.put(item);
        Random random = new Random();
        Thread.sleep(random.nextInt(2000));
    }
}
