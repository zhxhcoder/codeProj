package com.zhxh.codeproj.kotlin

/**
 * Created by zhxh on 2019/05/19
 */
object KRegexp {
    @JvmStatic
    fun main(args: Array<String>) {
        
        val s1 = Regex("\\d+").findAll("6732eed3").toList()
        val s2 = Regex("""\D+""").findAll("6732eed3").toList()
        val s3 = Regex("""\d+(?=$)""").findAll("6732eed3").toList()
        val s4 = Regex("""\d+$""").findAll("6732eed3").toList()
        val s5 = Regex("""\d+$""").find("6732eed3")
        
        s1.forEach {
            println("匹配值 " + it.value)
            println(it.range.start)
            println(it.range.endInclusive)
        }
        s2.forEach {
            println("匹配值 " + it.value)
            println(it.range.start)
            println(it.range.endInclusive)
        }
        s3.forEach {
            println("匹配值 " + it.value)
            println(it.range.start)
            println(it.range.endInclusive)
        }
        s4.forEach {
            println("匹配值 " + it.value)
            println(it.range.start)
            println(it.range.endInclusive)
        }
        println("********")
        println(s5?.groupValues)
    }
}
