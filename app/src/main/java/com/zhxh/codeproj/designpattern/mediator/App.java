package com.zhxh.codeproj.designpattern.mediator;

/*
 * 中介者模式（Mediator Pattern）是用来降低多个对象和类之间的通信复杂性。
 * 这种模式提供了一个中介类，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护。
 * 中介者模式属于行为型模式。
 * <p>
 * 主要解决：对象与对象之间存在大量的关联关系，这样势必会导致系统的结构变得很复杂，
 * 同时若一个对象发生改变，我们也需要跟踪与之相关联的对象，同时做出相应的处理。
 */
public class App {
    public static void main(String[] args) {
        User u1 = new User("zhxh");
        u1.sendMessage("Hi~ two222!");

        User u2 = new User("two222");
        u2.sendMessage("Hi~ zhxh!");
    }
}
