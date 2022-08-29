package com.zhxh.codeproj.javatest;


public class JavaTest5 {
    public static void main(String[] args) {
        /*
        Java 的 char 是两个字节，是怎么存 Utf-8 的字符的？

        char在 Java中是16位的，因为Java用的Unicode。不过8位的ASCII码包含在Unicode中，是从0~127的
        UTF-8 使用一至四个字节的序列对编码 Unicode 代码点进行编码。
         */
        System.out.println('a' - 'z');// -25

        /*
        Java String 可以有多长？

        string 最长有多长
        编译期：65534 = 2^16-1  (JVM的常量池最多可放65535个项)
        运行期：Integer.MAX_VALUE = 2147483647 = 2^31-1  由于java中字符是16位存储的，因此大概需要4GB内存
        */
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sb.append('0');
        }
        System.out.println(sb.toString().length());

        /*
        Java 的匿名内部类有哪些限制？

        编写时，没有名字，编译后名字变为$+数字的格式
        没有构造函数，构造函数由编译器在编译时创建
        定义在非静态作用域匿名类持有外部函数的引用
        继承声明二选一
        父类是非静态类型，则需父类外部实例来初始化
        只能使用外部作用域的final变量
        lambda只能声明单个方法的接口
         */



    }
}
