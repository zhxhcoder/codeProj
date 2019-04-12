package com.zhxh.codeproj.kotlin

/**
Created by zhxh on 2019/4/12
 */
interface Target {
    fun request()
}

interface Adaptee {
    fun ask()
}

class Adapter(val wrapper: Adaptee) : Target {
    override fun request() {
        wrapper.ask()
    }
}