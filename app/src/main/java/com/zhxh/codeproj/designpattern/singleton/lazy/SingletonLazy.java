package com.zhxh.codeproj.designpattern.singleton.lazy;

/**
 * Created by zhxh on 2016/10/28.
 * 3.单例模式的懒汉式
 */

public class SingletonLazy {

    private SingletonLazy() {
    }

    /**
     * 3.单例模式的懒汉式[线程不安全，不可用]
     */
    private static SingletonLazy singletonLazy;

    public static SingletonLazy getInstance() {
        if (singletonLazy == null) { // 这里线程是不安全的,可能得到两个不同的实例
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }

    /**
     * 4. 懒汉式线程安全的:[线程安全，效率低不推荐使用]
     * <p>
     * 缺点：效率太低了，每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步。
     * 而其实这个方法只执行一次实例化代码就够了，
     * 后面的想获得该类实例，直接return就行了。方法进行同步效率太低要改进。
     */
    private static SingletonLazy singletonLazyTwo;

    public static synchronized SingletonLazy getSingletonLazyTwo() {
        if (singletonLazyTwo == null) { // 这里线程是不安全的,可能得到两个不同的实例
            singletonLazyTwo = new SingletonLazy();
        }
        return singletonLazyTwo;
    }

    /**
     * 5. 单例模式懒汉式[线程不安全，不可用]
     * <p>
     * 虽然加了锁，但是等到第一个线程执行完instance=new Singleton()跳出这个锁时，
     * 另一个进入if语句的线程同样会实例化另外一个Singleton对象，线程不安全的原理跟3类似。
     */
    private static SingletonLazy instanceThree = null;

    public static SingletonLazy getSingletonLazyThree() {
        if (instanceThree == null) {
            synchronized (SingletonLazy.class) {// 线程不安全
                instanceThree = new SingletonLazy();
            }
        }
        return instanceThree;
    }

    /**
     * 6.单例模式懒汉式双重校验锁[推荐用]
     * 懒汉式变种,属于懒汉式的最好写法,保证了:延迟加载和线程安全
     */
    private static SingletonLazy singletonLazyFour;

    public static SingletonLazy getSingletonLazyFour() {
        if (singletonLazyFour == null) {
            synchronized (SingletonLazy.class) {
                if (singletonLazyFour == null) {
                    singletonLazyFour = new SingletonLazy();
                }
            }
        }
        return singletonLazyFour;
    }
}
