package com.zhxh.codeproj.designpattern.facade2;

public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.methdoA();
        facade.methodB();
    }
}
