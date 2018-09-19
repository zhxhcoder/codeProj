package com.zhxh.codeproj.dynamicproxy;

import java.util.Random;

/**
 * Bird
 *
 * @author zhxh 2018-02-28 15:42
 */
public class Bird implements Flyable {

    @Override
    public void fly() {
        System.out.println("鸟在飞...");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
