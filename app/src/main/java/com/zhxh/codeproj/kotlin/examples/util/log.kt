package com.zhxh.codeproj.kotlin.examples.util

import android.os.Build
import android.support.annotation.RequiresApi
import java.time.*

@RequiresApi(Build.VERSION_CODES.O)
fun log(msg: String) = println("${Instant.now()} [${Thread.currentThread().name}] $msg")