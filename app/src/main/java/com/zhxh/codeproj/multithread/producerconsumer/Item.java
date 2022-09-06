package com.zhxh.codeproj.multithread.producerconsumer;

/**
 * 参与 {@link Producer}-{@link Consumer} 交换的类
 */
public class Item {
    private String producer;
    private int id;

    public Item(String producer, int id) {
        this.id = id;
        this.producer = producer;
    }

    public int getId() {
        return id;
    }

    public String getProducer() {
        return producer;
    }
}
