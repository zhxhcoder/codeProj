package com.zhxh.codeproj.kotlin.examples.channel.example3

import com.zhxh.codeproj.kotlin.examples.channel.*

// https://tour.golang.org/concurrency/3

fun main(args: Array<String>) = mainBlocking {
    val c = Channel<Int>(2)
    c.send(1)
    c.send(2)
    println(c.receive())
    println(c.receive())
}