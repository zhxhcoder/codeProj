package com.zhxh.codeproj.kotlin.coroutines

/**
 * Created by zhxh on 2019/05/22
 */

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking<Unit> {
    // create a channel that produces numbers from 1 to 3 with 200ms delays between them
    val source = produce<Int> {
        println("开始") // mark the beginning of this coroutine in output
        for (x in 1..3) {
            delay(200) // wait for 200ms
            send(x) // send number x to the channel
        }
    }
    // print elements from the source
    println("Elements:")
    source.consumeEach {
        // consume elements from it
        println(it)
    }
    // print elements from the source AGAIN
    println("再次:")
    source.consumeEach {
        // consume elements from it
        println(it)
    }
}