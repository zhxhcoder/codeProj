package com.zhxh.codeproj.kotlin

import kotlinx.coroutines.*

/**
 * Created by zhxh on 2019/05/19
 */
object KCoroutines {

    fun mainExample(args: Array<String>) = runBlocking {

        val job = GlobalScope.launch {
            repeat(10) { i ->
                println("挂起中------$i")
                //每次循环，暂停1秒
                delay(1000L)

            }
        }
        val job2 = async {
            //挂起5秒
            delay(5000L)
            //使用注解标注此处返回的是 async 的闭包
            return@async "我是 async 返回的内容"
        }
        /**
         * await 是一个阻塞式方法
         * 会将主线程停在这里
         * 当 job2 挂起5秒结束，返回内容
         * await 接受到内容，主线程才继续向下执行-->开始等待
         */
        println("job2 返回的内容：${job2.await()}")
        println("主线程开始等待-----")
        delay(3000L)
        println("主线程等待结束-----取消launch开启的协程")
        job.cancel()//协程的启动和停止都是代码可控的

        println("主线程执行完毕，即将推出-----")
    }

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

        mainExample( arrayOf("1", "1", "1"))

        Thread.sleep(9100L) // 主线程阻塞以JVM保持激活 如果时间小于协程延迟则不输入协程打印
    }

    private fun function(): Int {
        return 999
    }

}
