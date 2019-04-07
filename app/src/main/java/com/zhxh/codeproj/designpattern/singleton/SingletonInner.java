package com.zhxh.codeproj.designpattern.singleton;

/**
 * Created by zhxh on 2019/4/7
 * 内部类的形式实现单例
 */
public class SingletonInner {
    private SingletonInner() {
    }

    public static SingletonInner getInstance() {
        return SingleHolder.inner;
    }

    private static class SingleHolder {
        private static final SingletonInner inner = new SingletonInner();
    }
}
