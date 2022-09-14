package com.zhxh.codeproj.designpattern.strategy2;

/**
 * Created by zhxh on 2019/4/14
 */
public class Client {
    public static void main(String[] args) {
        Strategy strategy1 = new ConcreteStrategy1();
        Strategy strategy2 = new ConcreteStrategy2();

        Context context1 = new Context(strategy1);
        context1.doAnything();

        Context context2 = new Context(strategy2);
        context2.doAnything();
    }
}
