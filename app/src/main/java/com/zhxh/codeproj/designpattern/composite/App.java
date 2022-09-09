package com.zhxh.codeproj.designpattern.composite;

import com.zhxh.codeproj.Log;

/*
 * 组合模式（Composite Pattern），又叫部分整体模式，是用于把一组相似的对象当作一个单一的对象。
 * 组合模式依据树形结构来组合对象，用来表示部分以及整体层次。
 * 这种类型的设计模式属于结构型模式，它创建了对象组的树形结构。
 * <p>
 * 主要解决：
 * 它在我们树型结构的问题中，模糊了简单元素和复杂元素的概念，
 * 客户程序可以像处理简单元素一样来处理复杂元素，从而使得客户程序与复杂元素的内部结构解耦。
 */
public class App {
    public static void main(String[] args) {

        // 2. 使用 Employee 类来创建和打印员工的层次结构。
        final Employee ceo = new Employee("John", "CEO", 30000);

        Employee headSales = new Employee("Robert", "Head sales", 20000);

        Employee headMarketing = new Employee("Michel", "Head Marketing", 20000);

        Employee clerk1 = new Employee("Laura", "Marketing", 10000);
        Employee clerk2 = new Employee("Bob", "Marketing", 10000);

        Employee salesExecutive1 = new Employee("Richard", "Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob", "Sales", 10000);

        ceo.add(headSales);
        ceo.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        Log.e("---", ceo.toString());
    }
}
