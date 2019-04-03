package com.zhxh.codeproj.java;

/**
 * Created by zhxh on 2019/4/3
 * nice
 */
public class JavaTest1 {
    public static void append(String str1, String str2) {
        str1 += str2;
    }

    public static void main(String[] args) {
        String str1 = "nice";
        String str2 = "zhxh";
        append(str1, str2);
        System.out.println(str1);
    }
}
