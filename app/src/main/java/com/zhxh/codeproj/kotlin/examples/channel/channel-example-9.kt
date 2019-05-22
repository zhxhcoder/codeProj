package com.zhxh.codeproj.kotlin.examples.channel.example9

import android.os.Build
import android.support.annotation.RequiresApi
import com.zhxh.codeproj.kotlin.examples.channel.*
import com.zhxh.codeproj.kotlin.examples.delay.*
import com.zhxh.codeproj.kotlin.examples.mutex.*

// https://tour.golang.org/concurrency/9

class SafeCounter {
    private val v = mutableMapOf<String, Int>()
    private val mux = Mutex()

    @RequiresApi(Build.VERSION_CODES.N)
    suspend fun inc(key: String) {
        mux.lock()
        try { v[key] = v.getOrDefault(key, 0) + 1 }
        finally { mux.unlock() }
    }

    suspend fun get(key: String): Int? {
        mux.lock()
        return try { v[key] }
        finally { mux.unlock() }
    }
}

fun main(args: Array<String>) = mainBlocking {
    val c = SafeCounter()
    for (i in 0..999) {
        go { c.inc("somekey") } // 1000 concurrent coroutines
    }
    delay(1000)
    println("${c.get("somekey")}")
}
