package com.zhxh.codeproj.kotlin.examples.context

import com.zhxh.codeproj.kotlin.examples.future.*
import com.zhxh.codeproj.kotlin.examples.run.*
import com.zhxh.codeproj.kotlin.examples.util.*

fun main(args: Array<String>) = runBlocking(CommonPool) {
    // multithreaded pool
    val n = 4
    val compute = newFixedThreadPoolContext(n, "Compute")
    // start 4 coroutines to do some heavy computation
    val subs = Array(n) { i ->
        future(compute) {
            log("Starting computation #$i")
            Thread.sleep(1000) // simulate long running operation
            log("Done computation #$i")
        }
    }
    // await all of them
    subs.forEach { it.await() }
    log("Done all")
}