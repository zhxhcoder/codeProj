package com.zhxh.codeproj.kotlin.examples.channel

import com.zhxh.codeproj.kotlin.examples.context.*
import com.zhxh.codeproj.kotlin.examples.run.*

fun mainBlocking(block: suspend () -> Unit) = runBlocking(CommonPool, block)

fun go(block: suspend () -> Unit) = CommonPool.runParallel(block)

