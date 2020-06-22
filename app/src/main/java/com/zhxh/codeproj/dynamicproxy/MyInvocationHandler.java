package com.zhxh.codeproj.dynamicproxy;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * MyInvocationHandler
 *
 * @author zhxh 2018-03-01 20:19
 */
public class MyInvocationHandler implements InvocationHandler {
    private Bird bird;

    public MyInvocationHandler(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void invoke(Object proxy, Method method, Object[] args) {
        long start = System.currentTimeMillis();

        try {
            method.invoke(bird, new Object[]{});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();
        System.out.println("飞行时间 = " + (end - start));
    }
}
