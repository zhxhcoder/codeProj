package com.cy.android.codeproj.abstractclass;

public class AbstractTest {

    /**
     * 按照普通类的继承方式 应该是先走完父类构造方法中的操作再走子类中的。
     * 抽象的触发父类draw()方法也连带着子类中的一同触发。并且是在子类并没有为
     * radius分配该有空间操作之前进行。因此radius的值并不是默认的初始值1，
     * 而是在未采取任何操作之前，系统默认的初始值0.
     *
     * @param args
     */
    public static void main(String[] args) {
        new RoundGraph(5);
    }
}
