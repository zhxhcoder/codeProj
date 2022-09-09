package com.zhxh.codeproj.designpattern.bridge;

/**
 * 3. 使用 DrawAPI 接口创建抽象类 Shape。
 */
public abstract class Shape {

    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
