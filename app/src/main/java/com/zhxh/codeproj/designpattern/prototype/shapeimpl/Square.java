package com.zhxh.codeproj.designpattern.prototype.shapeimpl;

import com.zhxh.codeproj.designpattern.prototype.Shape;
import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2020-01-31.
 * 2. 创建扩展了上面抽象类的实体类。Square 正方形
 */
public class Square extends Shape {

    public Square() {
        type = "Square";
    }

    @Override
    public void draw() {
        Log.e("---", "Square::draw()");
    }
}
