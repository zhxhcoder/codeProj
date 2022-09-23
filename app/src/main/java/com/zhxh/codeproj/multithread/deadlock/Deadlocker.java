package com.zhxh.codeproj.multithread.deadlock;

/*
coded by zhxh
死锁例子

Java线程死锁问题往往和一个被称之为哲学家就餐的问题相关联。
导致死锁的根源在于不适当地运用“synchronized”关键词来管理线程对特定对象的访问。
“synchronized”关键词的作用是，确保在某个时刻只有一个线程被允许执行特定的代码块，
因此，被允许执行的线程首先必须拥有对变量或对象的排他性的访问权。
当线程访问对象时，线程会给对象加锁，而这个锁导致其它也想访问同一对象的线程被阻塞，直至第一个线程释放它加在对象上的锁。
由于这个原因，在使用“synchronized”关键词时，很容易出现两个线程互相等待对方做出某个动作的情形。
 */
public class Deadlocker implements Runnable {
    public int flag = 1;
    static Object o1 = new Object(), o2 = new Object();

    public void run() {
        System.out.println("flag=" + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        Deadlocker td1 = new Deadlocker();
        Deadlocker td2 = new Deadlocker();
        td1.flag = 1;
        td2.flag = 0;
        Thread t1 = new Thread(td1);
        Thread t2 = new Thread(td2);
        t1.start();
        t2.start();
    }
}