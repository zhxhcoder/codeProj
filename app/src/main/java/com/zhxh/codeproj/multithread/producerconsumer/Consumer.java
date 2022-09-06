package com.zhxh.codeproj.multithread.producerconsumer;

/*
 负责消费由 {@link Producer} 生产的 {@link Item} 的类
  */
public class Consumer {
    private final ItemQueue queue;
    private final String name;

    public Consumer(String name, ItemQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    /*
     * 消费队列中的项目
     */
    public void consume() throws InterruptedException {
        Item item = queue.take();
        System.out.println(name + " consume item " + item.getId() + " produced by " + item.getProducer());
    }
}
