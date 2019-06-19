package com.zhxh.codeproj.javatest.java7;


/**
 * Created by zhxh on 2019/06/19
 */
public class MyNetService extends BaseNetService {
    @Override
    public String getCallAdapterFactory() {
        return "kotlin";
    }

    @Override
    public String getBaseUrl() {
        return "base";
    }
}
