package com.zhxh.codeproj.designpattern.iterator;

import com.zhxh.codeproj.Log;

/*
迭代器模式
目前已经是一个没落的模式
 * 迭代器模式（Iterator Pattern）是 Java 和 .Net 编程环境中非常常用的设计模式。
 * 这种模式用于顺序访问集合对象的元素，不需要知道集合对象的底层表示。
 * <p>
 * 主要解决：不同的方式来遍历整个整合对象。
 */
public class App {
    public static void main(String[] args) {
        //3. 使用 NameRepository 来获取迭代器，并打印名字。
        NameRepository nameRepository = new NameRepository();
        for (Iterator iterator = nameRepository.getIterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            Log.e("---", name);
        }
    }
}
