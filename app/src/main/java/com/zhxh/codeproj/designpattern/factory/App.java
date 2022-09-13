package com.zhxh.codeproj.designpattern.factory;

import com.zhxh.codeproj.designpattern.factory.abs.XianRoujiaMoTeSeStore;
import com.zhxh.codeproj.designpattern.factory.abs.XianSimpleRoujiaMoTeSeFactory;
import com.zhxh.codeproj.designpattern.factory.method.XianRoujiaMoStore;
import com.zhxh.codeproj.designpattern.factory.method.XianSimpleRoujiaMoFactory;
import com.zhxh.codeproj.designpattern.factory.simple.RoujiaMoStore;
import com.zhxh.codeproj.designpattern.factory.simple.SimpleRoujiaMoFactory;

/*
 * 今天继续设计模式之旅，给大家带来工厂模式，简单列一下这个模式的家族：
 * 1、静态工厂模式
 * 2、简单工厂模式
 * 3、工厂方法模式
 * 4、抽象工厂模式
 * <p>
 * <p>
 * 1、静态工厂模式
 * 这个最常见了，项目中的辅助类，TextUtil.isEmpty等，类+静态方法。下面开始详细介绍：略
 */
public class App {
    public static void main(String[] args) {
        //1.静态工厂模式


        //2.简单工厂模式 (店里卖肉夹馍)
        // 通过专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类。
        RoujiaMoStore roujiaMoStore = new RoujiaMoStore(new SimpleRoujiaMoFactory());
        roujiaMoStore.sellRoujiaMo("Suan");
        roujiaMoStore.sellRoujiaMo("Tian");
        roujiaMoStore.sellRoujiaMo("La");

        // 3.工厂方法模式 (开分店)
        // 定义：定义一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法模式把类实例化的过程推迟到子类。
        XianRoujiaMoStore xianRoujiaMoStore = new XianRoujiaMoStore(new XianSimpleRoujiaMoFactory());// 分店简单工厂
        xianRoujiaMoStore.sellRoujiaMo("Suan");
        xianRoujiaMoStore.sellRoujiaMo("Tian");
        xianRoujiaMoStore.sellRoujiaMo("La");

        // 4.抽象工厂模式 (使用官方提供的原料)
        // 定义：提供一个接口，用于创建相关的或依赖对象的家族，而不需要明确指定具体类。
        XianRoujiaMoTeSeStore xianRoujiaMoTeSeStore = new XianRoujiaMoTeSeStore(new XianSimpleRoujiaMoTeSeFactory());
        xianRoujiaMoTeSeStore.sellRoujiaMo("suan");
    }
}
