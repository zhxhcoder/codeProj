package com.zhxh.codeproj.designpattern.prototype;

import com.zhxh.codeproj.Log;

/*
 * 原型模式（Prototype Pattern）
 * 是用于创建重复的对象，同时又能保证性能。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 * 使用场景：
 * 这种模式是实现了一个原型接口，该接口用于创建当前对象的克隆。当直接创建对象的代价比较大时，则采用这种模式。
 * 例如，一个对象需要在一个高代价的数据库操作之后被创建。我们可以缓存该对象，在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库调用。
 * 步骤：
 * 1. 创建一个实现了 Cloneable 接口的抽象类。Shape（implements Cloneable）
 * 2. 创建扩展了上面抽象类的实体类。Circle Rectangle Square
 * 3. 创建一个类，从数据库获取实体类，并把它们存储在一个 Hashtable 中。ShapeCache
 * 4. 使用 ShapeCache 类来获取存储在 Hashtable 中的形状的克隆。
 */
public class App {
    public static void main(String[] args) {
        // 使用 ShapeCache 类来获取存储在 Hashtable 中的形状的克隆。
        ShapeCache.loadCache();

        Shape shapeCache1 = ShapeCache.getShape("1");
        Log.e("Prototype", "Shape: " + shapeCache1.type);


        Shape shapeCache2 = ShapeCache.getShape("2");
        Log.e("Prototype", "Shape: " + shapeCache2.type);

        Shape shapeCache3 = ShapeCache.getShape("3");
        Log.e("Prototype", "Shape: " + shapeCache3.type);
    }
}
