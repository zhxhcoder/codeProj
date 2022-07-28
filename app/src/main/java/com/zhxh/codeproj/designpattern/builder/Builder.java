package com.zhxh.codeproj.designpattern.builder;

/**
 * Created by zhxh on 2019/4/9
 */
public abstract class Builder {
    public abstract void setPart();

    public abstract Product buildProduct();
}
