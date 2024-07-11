package com.zhxh.codeproj.javatest;

/*
 * Created by zhxh on 2024/7/11
 *
 * ByteArrayToString
 *
 * Java byte数组转String
 */
public class JavaTest10 {

    public static void main(String[] args) {
        byte[] byteArray = {104, 101, 108, 108, 111}; // "hello" 的 ASCII 编码
        String str = new String(byteArray, "UTF-8"); // 指定编码格式为 UTF-8
        System.out.println(str);
    }
}
