package com.zhxh.codeproj.kotlin

/**
 * Created by zhxh on 2019/05/19
 */

object TestKotlin {


    fun print1() {
        val list: List<Int>? = initElements()
        val maxElement = list?.let { it.max() } ?: -1
        return println("返回 $maxElement")
    }

    fun print2() {
        val list: List<Int>? = initElements()
        val maxElement = if (list != null) list.max() else -1
        return println("返回 $maxElement")
    }

    fun initElements(): List<Int>? = emptyList()

    @JvmStatic
    fun main(args: Array<String>) {

        println("*****************let-?: 判空*******************")

        print1()

        println("*****************if-else 判空*********************")

        print2()
    }

    private fun createElements(): List<Int> {
        return listOf(11, 33, 22)
    }
}
