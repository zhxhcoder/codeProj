package com.zhxh.codeproj.java.javatest4;

/**
 * Created by zhxh on 2019/4/3
 */
public class Parent {
    public static String p_static_field = "p_static_field";
    public String p_field = "p_field";

    static {
        System.out.println(p_static_field);
        System.out.println("p_static_field_over");
    }

    {
        System.out.println(p_field);
        System.out.println("p_field_over");
    }

    public Parent(){
        System.out.println("create_parent");
    }
}
