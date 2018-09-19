package com.zhxh.codeproj.dynamicproxy;

/**
 * BirdLogProxy
 *
 * @author zhxh 2018-03-01 10:53
 */
public class BirdLogProxy implements Flyable {
    private Flyable flyable;

    public BirdLogProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        System.out.println("鸟开始起飞...");

        flyable.fly();

        System.out.println("鸟已经飞完...");
    }
}
