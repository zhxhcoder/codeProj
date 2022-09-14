package com.zhxh.codeproj.designpattern.proxy2;

public class Client {
    public static void main(String[] args) {
        RealSubject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);
        proxy.request();
    }
}
