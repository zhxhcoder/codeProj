package com.zhxh.codeproj.designpattern.singleton;

/**
 * Created by zhxh on 2019/4/7
 * 双重检验的模式的单例
 */
public class SingletonCheck {
    private volatile static SingletonCheck instance;

    private SingletonCheck() {
    }

    public static SingletonCheck getInstance() {
        if (instance == null) {
            synchronized (SingletonCheck.class) {
                if (instance == null) {
                    instance = new SingletonCheck();
                }
            }
        }
        return instance;
    }
}
