package com.zhxh.codeproj.kotlin.examples.context

import com.zhxh.codeproj.kotlin.examples.run.*
import kotlin.coroutines.*

suspend fun doSomething() {
    val currentUser = coroutineContext[AuthUser]?.name ?: throw SecurityException("unauthorized")
    println("Current user is $currentUser")
}

fun main(args: Array<String>) {
    runBlocking(AuthUser("admin")) {
        doSomething()
    }
}
