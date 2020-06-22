package com.zhxh.codeproj.kotlin

/**
 * Created by zhxh on 2019/05/19
 */
object KRegexp {
    @JvmStatic
    fun main(args: Array<String>) {

        val strT = "*海清：科学家不要把自己的道德底线抛弃\n*小亮：科虚假不要把急急急科虚假不要把急急急\n*吴玉回复@海清：说得好"

        println("************多行************")
        val s0 = Regex("""^\*.+：|(?<=\n)\*.+：""".trimMargin()).findAll(strT.trimMargin()).toList()

        s0.forEach {
            println("多行匹配值-> " + it.value)
            println(it.range.start)
            println(it.range.endInclusive)
        }

        println("************find************")

        val s1 = Regex("\\d+").findAll("6732eed3").toList()
        val s2 = Regex("""\d+(?=$)""").findAll("6732eed3").toList()
        val s3 = Regex("""\d+$""").find("6732eed3")

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
        println(s3?.groupValues)
        println(s3?.range)
        println(s3?.next()?.value)

        println("************matches************")
        //输入的字符串全部匹配“正则表达式”返回 true，否则返回 false。

        val r1 = Regex("[a-z]+")
        println(r1.matches("ABCdef"))
        val r2 = Regex("[a-z]+", RegexOption.IGNORE_CASE) //忽略大小写
        println(r2.matches("ABCdef"))
        val r3 = Regex("[A-Z]+")
        println(r3.matches("ABC"))
        val r4 = "[A-Z]+".toRegex()
        println(r4.matches("ABCDEFXYZ"))

        println("************containsMatchIn************")
        //输入的字符串中至少有一个匹配就返回 true，否则就返回 false。

        val r5 = Regex("[0-9]+")
        println(r5.containsMatchIn("012abdc"))
        println(r5.containsMatchIn("JAVAI"))

        println("************matchEntire************")
        //对字符串全部做比较，都匹配正则表达式返回一个MatcherMatchResult对象，否则返回 null。

        val r6 = Regex("[0-9]+")
        println(r6.matchEntire("1234567890"))
        println(r6.matchEntire("1234567890!"))
        println(r6.matchEntire("1234567890")?.value)

        println("************replace************")
        //replace(input: CharSequence, replacement: String): String

        val r7 = Regex("[0-9]+")
        println(r7.replace("12345XYZ33", "abcd"))

        println("************replace函数************")
        //函数签名 replace(input: CharSequence, transform: (MatchResult)->CharSequence): String
        //它的功能是把输入的字符串中匹配的值，用函数 transform 映射之后的新值进行替换。

        val r8 = Regex("[0-9]+")
        println(r8.replace("12XYZ9", { (it.value.toInt() * it.value.toInt()).toString() }))

        println("************find函数************")
        //返回字符串中第一个匹配的 MatcherMatchResult 对象

        val r9 = Regex("[0-9]+")
        println(r9.find("123ADPOIW87WEERUU005"))
        println(r9.find("123ADPOIW87WEERUU005")?.value)


        println("************findAll函数************")
        //返回输入的字符串中所有匹配的值的 MatchResult 序列。

        val src2 = "电话:010-12345678;传真:010-10171695;备用:010-20141017"
        val re2 = """(\d{3}-\d{8})""" //"\\d{3}-\\d{8}"
        Regex(re2).findAll(src2).forEach { println(it.value + " 位置：[" + it.range + "]") }

    }
}
