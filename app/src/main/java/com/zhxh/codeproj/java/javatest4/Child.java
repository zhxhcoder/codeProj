package com.zhxh.codeproj.java.javatest4;

/**
 * Created by zhxh on 2019/4/3
 p_static_field
 p_static_field_over
 c_static_field
 c_static_field_over
 p_field
 p_field_over
 p_create_parent
 c_field
 c_field_over
 c_create_child
 */
public class Child extends Parent {
    public static String c_static_field = "c_static_field";
    public String c_field = "c_field";


    static {
        System.out.println(c_static_field);
        System.out.println("c_static_field_over");
    }

    {
        System.out.println(c_field);
        System.out.println("c_field_over");
    }

    public Child() {
        System.out.println("c_create_child");
    }

    public static void main(String[] args){
        new Child();
    }
}
