package com.zhxh.codeproj.kotlin

/**
 * Created by zhxh on 2019/05/19
 */

object TestKotlin {


    @JvmStatic
    fun main(args: Array<String>) {
        val list1: List<Int>? = emptyElements()
        val maxElement1 = list1?.let { it.max() } ?: return
        println("#1#最大值 $maxElement1")

        println("************************************")

        val list2: List<Int>? = emptyElements()
        val maxElement2 = if (list2 != null) list2.max() else return
        println("#2#最大值 $maxElement2")
    }

    fun emptyElements(): List<Int>? = emptyList()
    private fun createElements(): List<Int> {
        return listOf(11, 33, 22)
    }
}
