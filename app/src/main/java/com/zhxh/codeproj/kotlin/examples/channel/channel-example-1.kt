package com.zhxh.codeproj.kotlin.examples.channel.example1

import com.zhxh.codeproj.kotlin.examples.channel.*
import com.zhxh.codeproj.kotlin.examples.delay.*

// https://tour.golang.org/concurrency/1

suspend fun say(s: String) {
    for (i in 0..4) {
        delay(100)
        println(s)
    }
}

fun main(args: Array<String>) = mainBlocking {
    go { say("world") }
    say("hello")
}

