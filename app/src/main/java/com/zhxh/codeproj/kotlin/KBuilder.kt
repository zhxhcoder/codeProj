package com.zhxh.codeproj.kotlin

/**
Created by zhxh on 2019/4/10
 */

class KBuilder(var name: String, var age: Int) {

    constructor(builder: Builder) : this(builder.name, builder.age)

    companion object {
        inline fun build(block: Builder.() -> Unit): KBuilder {
            return Builder().apply(block).build()
        }
    }

    class Builder {
        var name: String = ""
        var age: Int = 0

        fun build(): KBuilder {
            return KBuilder(this)
        }
    }
}
