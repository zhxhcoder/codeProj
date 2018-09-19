package com.zhxh.codeproj.dynamicproxy;

/**
 * BirdTimeProxy
 *
 * @author zhxh 2018-03-01 10:53
 */
public class BirdTimeProxy implements Flyable {
    private Flyable flyable;

    public BirdTimeProxy(Flyable flyable) {
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        long start = System.currentTimeMillis();

        flyable.fly();

        long end = System.currentTimeMillis();
        System.out.println("飞行时间 = " + (end - start));
    }
}
