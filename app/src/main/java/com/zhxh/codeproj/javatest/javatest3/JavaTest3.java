package com.zhxh.codeproj.javatest.javatest3;

/**
 * Created by zhxh on 2019/4/3
 * Man10
 * Man
 * Man20
 * Person
 * Man
 */
public class JavaTest3 {

    public static void main(String[] args) {
        Man man = new Man();
        man.print(10);
        System.out.println(man.name);

        Person person = man;
        person.print(20);
        System.out.println(person.name);
        System.out.println(((Man) person).name);
    }
}
