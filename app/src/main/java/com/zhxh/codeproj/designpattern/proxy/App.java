package com.zhxh.codeproj.designpattern.proxy;

/*
 * 在代理模式（Proxy Pattern）中，一个类代表另一个类的功能。这种类型的设计模式属于结构型模式。
 * <p>
 * 在代理模式中，我们创建具有现有对象的对象，以便向外界提供功能接口。
 * 意图：为其他对象提供一种代理以控制对这个对象的访问。
 * <p>
 * 主要解决：在直接访问对象时带来的问题，
 * 比如说：要访问的对象在远程的机器上。
 * 在面向对象系统中，有些对象由于某些原因（比如对象创建开销很大，或者某些操作需要安全控制，或者需要进程外的访问），
 * 直接访问会给使用者或者系统结构带来很多麻烦，我们可以在访问此对象时加上一个对此对象的访问层。
 */
public class App {
    public static void main(String[] args) {

        // 3. 当被请求时，使用 ProxyImage 来获取 RealImage 类的对象。
        final Image image = new ProxyImage("test_10mb.png");

        // 第一次是new的，图像从磁盘加载
        image.display();

        // 第二次取缓存，图像不需要从磁盘加载
        image.display();

    }
}
