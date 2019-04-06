package com.zhxh.codeproj.javatest;

import java.util.Arrays;

/**
 * Created by zhxh on 2019/4/3
 * [6, 8, 10, 12]
 * [6, 8, 10, 12]
 * 6
 * 6
 */
public class JavaTest2 {
    private static void changeValue(int a) {
        a *= 2;
    }

    private static void changeValue(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 2;
        }
    }

    private static void changeValue(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 2;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 4, 5, 6};
        Integer[] arr2 = {3, 4, 5, 6};
        int a1 = 6;
        Integer a2 = Integer.valueOf(6);
        changeValue(arr1);
        changeValue(arr2);
        changeValue(a1);
        changeValue(a2);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(a1);
        System.out.println(a2);
    }
}
