package com.zhxh.codeproj.multithread;

public class KeywordStop {
    public static void main(String[] args) throws InterruptedException {
        KeywordStopThread mThread = new KeywordStopThread("a");
        new Thread(mThread).start();
        Thread.sleep(3000);
        mThread.isExit = true;
    }
}

class KeywordStopThread implements Runnable {
    private String name;

    public KeywordStopThread(String name) {
        this.name = name;
    }

    /*
    退出线程的标志
    volatile是因为可以保证有序性和可见性
    这里需要修改 isExit 以后里面对主内存可见。
     */
    public volatile boolean isExit = false;

    @Override
    public void run() {
        while (!isExit) {
            try {
                Thread.sleep(1000);
                System.out.println("线程 " + name + " 正在运行中");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程终止");
    }
}