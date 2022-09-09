package com.zhxh.codeproj.designpattern.iterator;

/*
迭代器模式
目前已经是一个没落的模式

 * 解释器模式（Interpreter Pattern）提供了评估语言的语法或表达式的方式，它属于行为型模式。
 * 这种模式实现了一个表达式接口，该接口解释一个特定的上下文。这种模式被用在 SQL 解析、符号处理引擎等。
 * <p>
 * 主要解决：对于一些固定文法构建一个解释句子的解释器。
 */
public class Client {
    public static void main(String[] args) {
        //3. 使用 NameRepository 来获取迭代器，并打印名字。
        NameRepository nameRepository = new NameRepository();
        for (Iterator iterator = nameRepository.getIterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            System.out.println("---" + name);
        }
    }
}
