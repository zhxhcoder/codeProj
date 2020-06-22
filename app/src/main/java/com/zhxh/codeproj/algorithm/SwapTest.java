package com.zhxh.codeproj.algorithm;

import java.lang.reflect.Field;

/**
 * Created by zhxh on 2019/4/11
 */
public class SwapTest {
    public static void main(String[] args) {
        Integer a = 2;
        Integer b = 3;
        System.out.println("a=" + a + " b=" + b);
        //这样是可以直接转换的
        //a = a + b;
        //b = a - b;
        //a = a - b;

        //swap(a,b);
        try {
            swapField(a, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("a=" + a + " b=" + b);
    }

    //无论是int还是Integer都是无法转换的 因为是值传递，自动
    private static void swap(Integer a, Integer b) {
        a = a + b;
        b = a - b;
        a = a - b;
    }


    private static void swapField(Integer a, Integer b) throws Exception {
        int temp = a;
        Class<? extends Integer> aClass = a.getClass();
        Field aValue = aClass.getDeclaredField("value");
        aValue.setAccessible(true);
        aValue.set(a, b);

        Class<? extends Integer> bClass = b.getClass();
        Field bValue = bClass.getDeclaredField("value");
        bValue.setAccessible(true);
        bValue.set(b, new Integer(temp));
    }

}
