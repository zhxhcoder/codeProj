package com.zhxh.codeproj.dynamicproxy;

/**
 * Bird2
 *
 * @author zhxh 2018-03-01 10:48
 */
public class Bird2 extends Bird {

    @Override
    public void fly() {
        long start = System.currentTimeMillis();

        super.fly();

        long end = System.currentTimeMillis();
        System.out.println("飞行时间 = " + (end - start));
    }
}
