package com.zhxh.codeproj.kotlin

/**
 * Created by zhxh on 2019/05/19
 */

object TestKotlin {

    //?:操作符，elvis操作符，这个其实和可空类型没啥关系，这个也不是Java中的三目运算符，容易混淆
    //仅仅在左边的表达式结果为空时才会计算?:后面的表达式
    fun print1() {
        val list: List<Int>? = initElements()
        val maxElement = list?.let { it.max() } ?: -1
        return println("返回 $maxElement")
    }

    //maxElement的数据类型最后才会确定
    fun print2() {
        val list: List<Int>? = initElements()
        val maxElement = if (list != null) list.max() else -1
        return println("返回 $maxElement")
    }

    fun initElements(): List<Int>? = emptyList()

    @JvmStatic
    fun main(args: Array<String>) {

        println("*****************let-?: 判空*******************")

        //返回 -1
        print1()

        println("*****************if-else 判空*********************")

        //返回 null
        print2()
    }

    private fun createElements(): List<Int> {
        return listOf(11, 33, 22)
    }
}
