package com.zhxh.codeproj.javatest;

import java.util.Arrays;

/**
 * Created by zhxh on 2019/4/3
 * [6, 8, 10, 12]
 */
public class JavaTest2 {
    private static void changeValue(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 6};
        changeValue(arr);
        System.out.println(Arrays.toString(arr));
    }
}
