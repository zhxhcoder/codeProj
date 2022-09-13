package com.zhxh.codeproj.designpattern.strategy2;

/**
 * Created by zhxh on 2019/4/14
 */
public class Client {
    public static void main(String[] args) {
        Strategy strategy1 = new ConcreteStrategy1();

        Context context = new Context(strategy1);
        context.doAnything();

    }
}
