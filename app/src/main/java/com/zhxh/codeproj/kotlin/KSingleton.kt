package com.zhxh.codeproj.kotlin

/**
Created by zhxh on 2019/4/9
Kotlin实现双重验证的单例
 */
class KSingleton private constructor() {
    companion object {
        val instance: KSingleton by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            KSingleton()
        }
    }
}
