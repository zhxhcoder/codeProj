package com.zhxh.codeproj.java.javatest3;

/**
 * Created by zhxh on 2019/4/3
 *
 * Man10
 * Man
 * Man20
 * Person
 */
public class JavaTest3 {

    public static void main(String[] args) {
        Man man = new Man();
        man.print(10);
        System.out.println(man.name);

        Person person = man;
        person.print(20);
        System.out.println(person.name);
    }
}
