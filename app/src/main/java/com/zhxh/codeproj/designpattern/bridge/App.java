package com.zhxh.codeproj.designpattern.bridge;

/*
 * 桥接（Bridge）是用于把抽象化与实现化解耦，使得二者可以独立变化。
 * 这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦。
 * <p>
 * 这种模式涉及到一个作为桥接的接口[DrawAPI]，使得实体类的功能独立于接口实现类。这两种类型的类可被结构化改变而互不影响。
 */
public class App {
    public static void main(String[] args) {
        // 画红圆
        Circle circle = new Circle(10, 10, 100, new RedCircle());
        circle.draw();


        // 画绿圆
        Circle circle2 = new Circle(20, 20, 100, new GreenCircle());
        circle2.draw();
    }
}
