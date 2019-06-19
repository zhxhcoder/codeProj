package com.zhxh.codeproj.javatest.java7;

/**
 * Created by zhxh on 2019/06/19
 */
public abstract class BaseNetService {
    public abstract String getBaseUrl();

    public String getCallAdapterFactory() {
        return "rxJava";
    }

    public void createService() {
        System.out.println("xxxxxx1xxxxxxx " + getBaseUrl());
        System.out.println("xxxxxx2xxxxxxx " + getCallAdapterFactory());
    }
}
