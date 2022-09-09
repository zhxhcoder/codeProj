package com.zhxh.codeproj.designpattern.builder;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2020-01-31.
 * 产品类
 */
public class Product {

    private String name;
    private String type;

    public void showProduct() {
        Log.e("---", "名称：" + name);
        Log.e("---", "型号：" + type);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
