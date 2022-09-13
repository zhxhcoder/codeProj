package com.zhxh.codeproj.designpattern.prototype.shapeimpl;

import com.zhxh.codeproj.designpattern.prototype.Shape;
import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2020-01-31.
 * 2. 创建扩展了上面抽象类的实体类。Circle 圆形
 */
public class Circle extends Shape {

    public Circle() {
        type = "Circle";
    }

    @Override
    public void draw() {
        Log.e("---", "Circle::draw()");
    }
}
