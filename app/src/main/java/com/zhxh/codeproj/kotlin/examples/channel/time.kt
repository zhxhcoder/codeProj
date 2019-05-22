package com.zhxh.codeproj.kotlin.examples.channel

import com.zhxh.codeproj.kotlin.examples.delay.*
import java.time.*

object Time {
    fun tick(millis: Long): ReceiveChannel<Instant> {
        val c = Channel<Instant>()
        go {
            while (true) {
                delay(millis)
                c.send(Instant.now())
            }
        }
        return c
    }

    fun after(millis: Long): ReceiveChannel<Instant> {
        val c = Channel<Instant>()
        go {
            delay(millis)
            c.send(Instant.now())
            c.close()
        }
        return c
    }
}
