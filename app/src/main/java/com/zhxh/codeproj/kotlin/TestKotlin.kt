package com.zhxh.codeproj.kotlin

/**
 * Created by zhxh on 2019/05/19
 */

object TestKotlin {


    fun print1() {
        val maxElement = emptyElements()?.let { it.max() } ?: return
        println("#1#最大值 $maxElement")
    }

    fun print2() {
        val list: List<Int>? = emptyElements()
        val maxElement = if (list != null) list.max() else return
        println("#2#最大值 $maxElement")
    }

    fun emptyElements(): List<Int>? = emptyList()

    @JvmStatic
    fun main(args: Array<String>) {

        println("*****************let ?:p*******************")

        print1()

        println("***************if else 判空*********************")

        print2()
    }

    private fun createElements(): List<Int> {
        return listOf(11, 33, 22)
    }
}
