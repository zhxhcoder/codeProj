package com.zhxh.codeproj;

public class Log {

    private static final String dot = " ";

    public static void e(String tag, String msg) {
        System.out.println(tag + dot + msg);
    }

    public static void d(String tag, String msg) {
        System.out.println(tag + dot + msg);
    }

    public static void i(String tag, String msg) {
        System.out.println(tag + dot + msg);
    }

    public static void v(String tag, String msg) {
        System.out.println(tag + dot + msg);
    }


    public static void e(String msg) {
        System.out.println(msg);
    }

    public static void d(String msg) {
        System.out.println(msg);
    }

    public static void i(String msg) {
        System.out.println(msg);
    }

    public static void v(String msg) {
        System.out.println(msg);
    }

}
