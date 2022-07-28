package com.zhxh.codeproj.javatest;

/**
 * Created by zhxh on 2019/4/3
 * nice
 */
public class JavaTest1 {
    public static void main(String[] args) {
        String str = "abc";
        String str1 = "abc";
        String str2 = new String("abc");
        System.out.println(str == str1);
        System.out.println(str1 == "abc");
        System.out.println(str2 == "abc");
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
        System.out.println(str1 == str2.intern());
        System.out.println(str2 == str2.intern());
        System.out.println(str1.hashCode() == str2.hashCode());
    }
}
