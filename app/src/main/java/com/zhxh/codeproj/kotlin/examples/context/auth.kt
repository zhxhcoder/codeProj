package com.zhxh.codeproj.kotlin.examples.context

import kotlin.coroutines.*

class AuthUser(val name: String) : AbstractCoroutineContextElement(AuthUser) {
    companion object Key : CoroutineContext.Key<AuthUser>
}
