package com.zhxh.codeproj.kotlin.examples.future

import android.os.Build
import android.support.annotation.RequiresApi
import java.util.concurrent.*
import kotlin.coroutines.*

@RequiresApi(Build.VERSION_CODES.N)
suspend fun <T> CompletableFuture<T>.await(): T =
    suspendCoroutine<T> { cont: Continuation<T> ->
        whenComplete { result, exception ->
            if (exception == null) // the future has been completed normally
                cont.resume(result)
            else // the future has completed with an exception
                cont.resumeWithException(exception)
        }
    }
