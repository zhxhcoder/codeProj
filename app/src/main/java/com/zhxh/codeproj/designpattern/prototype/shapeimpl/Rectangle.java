package com.zhxh.codeproj.designpattern.prototype.shapeimpl;

import com.zhxh.codeproj.designpattern.prototype.Shape;
import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2020-01-31.
 * 2. 创建扩展了上面抽象类的实体类。Rectangle 矩形
 */
public class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    public void draw() {
        Log.e("---", "Rectangle::draw()");
    }
}
