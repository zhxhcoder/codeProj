package com.zhxh.codeproj.kotlin

import kotlinx.coroutines.*

/**
 * Created by zhxh on 2019/05/19
 */
object KCoroutines {
    @JvmStatic
    fun main(args: Array<String>) {
        
        GlobalScope.launch {
            //新建一个后台协程
            delay(5000L) //非阻塞延迟5秒
            println("协程打印") // print after delay
        }
        
        println("主线程打印")
        Thread.sleep(5100L) // 主线程阻塞以JVM保持激活 如果时间小于协程延迟则不输入协程打印
    }
}
