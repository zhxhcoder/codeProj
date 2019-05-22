package com.zhxh.codeproj.kotlin.examples.context

import android.os.Build
import android.support.annotation.RequiresApi
import com.zhxh.codeproj.kotlin.examples.delay.*
import com.zhxh.codeproj.kotlin.examples.future.*
import com.zhxh.codeproj.kotlin.examples.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun main(args: Array<String>) {
    log("Starting MyEventThread")
    val context = newSingleThreadContext("MyEventThread")
    val f = future(context) {
        log("Hello, world!")
        val f1 = future(context) {
            log("f1 is sleeping")
            delay(1000) // sleep 1s
            log("f1 returns 1")
            1
        }
        val f2 = future(context) {
            log("f2 is sleeping")
            delay(1000) // sleep 1s
            log("f2 returns 2")
            2
        }
        log("I'll wait for both f1 and f2. It should take just a second!")
        val sum = f1.await() + f2.await()
        log("And the sum is $sum")
    }
    f.get()
    log("Terminated")
}
