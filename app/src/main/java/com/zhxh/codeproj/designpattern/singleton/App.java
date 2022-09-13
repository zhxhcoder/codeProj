package com.zhxh.codeproj.designpattern.singleton;

import com.zhxh.codeproj.designpattern.singleton.hungry.SingletonHungry;
import com.zhxh.codeproj.designpattern.singleton.enums.SingletonEnum;
import com.zhxh.codeproj.designpattern.singleton.inclass.SingletonIn;
import com.zhxh.codeproj.designpattern.singleton.lazy.SingletonLazy;

/*
<em>单例模式（Singleton Pattern）</em>：主要是为了避免因为创建了多个实例造成资源的浪费，
且多个实例由于多次调用容易导致结果出现错误，而使用单例模式能够保证整个应用中有且只有一个实例。
 */
public class App {
    public static void main(String[] args) {
        // 饿汉式[可用]
        SingletonHungry instance = SingletonHungry.getInstance();

        // 懒汉式[双重校验锁 推荐用]
        SingletonLazy singletonLazyOne = SingletonLazy.getInstance();
        SingletonLazy singletonLazyTwo = SingletonLazy.getSingletonLazyTwo();
        SingletonLazy singletonLazyThree = SingletonLazy.getSingletonLazyThree();
        SingletonLazy singletonLazyFour = SingletonLazy.getSingletonLazyFour();

        //内部类[推荐用]
        SingletonIn singletonIn = SingletonIn.getSingletonIn();

        // 枚举[推荐用]
        SingletonEnum.instance.whateverMethod();
    }
}
