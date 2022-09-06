package com.zhxh.codeproj.multithread.producerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 生产者消费者设计模式是一种经典的并发或线程模式，它减少了之间的耦合
 生产者和消费者通过将工作识别与工作执行分开。

 在生产者消费者设计模式中，共享队列用于控制流程，这种分离允许您编写代码
 生产者和消费者分开。 它还解决了不同时间需要生产项目或
 消费项目。 通过使用生产者消费者模式，生产者和消费者线程可以以不同的速度工作。

 */
public class App {
    public static void main(String[] args) {
        //信息队列
        ItemQueue queue = new ItemQueue();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 2; i++) {
            final Producer producer = new Producer("生产者-" + i, queue);
            executorService.submit(() -> {
                while (true) {
                    producer.produce();
                }
            });
        }

        for (int i = 0; i < 3; i++) {
            final Consumer consumer = new Consumer("消费者-" + i, queue);
            executorService.submit(() -> {
                while (true) {
                    consumer.consume();
                }
            });
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            System.out.println("错误，等待 ExecutorService 关闭");
        }
    }
}
