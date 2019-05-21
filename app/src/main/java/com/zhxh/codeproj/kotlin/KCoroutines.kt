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

        val job = GlobalScope.launch(Dispatchers.Default) {
            //在一个协程环境中，执行后台耗时操作
            //新建一个后台协程
            delay(3000L) //非阻塞延迟5秒
            println("Default协程打印") // print after delay
        }

        GlobalScope.launch(newSingleThreadContext("MyThread")) {
            //在一个协程环境中，执行后天耗时操作
            //newSingleThreadContext 会为协程运行启动一个新定线程。既然起了新定线程，那么协程就没啥意义了
            delay(2000L) //非阻塞延迟5秒
            println("newSingleThreadContext协程打印") // print after delay
        }

        //正常在Java中，我们想在thread中得到返回值。一般会通过callback

        GlobalScope.launch {
            val result = async {
                function()
            }
            println("zzzz-${result.await()}")
        }

        job.cancel()

        Thread.sleep(9100L) // 主线程阻塞以JVM保持激活 如果时间小于协程延迟则不输入协程打印
    }

    private fun function(): Int {
        return 999
    }

}
