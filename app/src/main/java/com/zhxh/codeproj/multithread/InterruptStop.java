package com.zhxh.codeproj.multithread;

public class InterruptStop {
    public static void main(String[] args) {
        InterruptStopThread mThread = new InterruptStopThread("a");
        mThread.start();
        try {
            Thread.sleep(500);
            System.out.println("线程终止");
            mThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class InterruptStopThread extends Thread {
    private String name;

    public InterruptStopThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // isInterrupted() 是否中断，中断退出，不中断继续执行
        while (!isInterrupted()) {
            System.out.println("线程 " + name + " 正在运行中");
        }
        System.out.println("线程终止");
    }
}