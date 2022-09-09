package com.zhxh.codeproj.designpattern.flyweight;

import com.zhxh.codeproj.Log;

import java.util.HashMap;

/**
 * Created by zhxh on 2020-02-01.
 * 3. 创建一个工厂，生成基于给定信息的实体类的对象。
 */
public class ShapeFactory {

    private static final HashMap<String, Shape> circleMap = new HashMap<String, Shape>();

    public static Shape getShape(String color) {
        Shape shape = circleMap.get(color);
        if (shape == null) {
            shape = new Circle(color);
            circleMap.put(color, shape);
            Log.e("getShape", "Creating circle of color : " + color);
        }
        return shape;
    }
}
