package com.zhxh.codeproj.designpattern.proxy;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2020-02-01.
 * 2. 创建实现接口的实体类。
 */
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        Log.e("RealImage", "loading " + fileName);
    }

    @Override
    public void display() {
        Log.e("RealImage", "Displaying " + fileName);
    }
}
