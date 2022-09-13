package com.zhxh.codeproj.designpattern.factory.method;

/**
 * Created by zhxh on 2019/4/7
 */
public abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> c);
}
