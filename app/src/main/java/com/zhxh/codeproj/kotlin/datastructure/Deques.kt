package com.zhxh.codeproj.kotlin.datastructure

import com.zhxh.codeproj.kotlin.datastructure.utils.Result

interface Queue<E> : MutableIterable<E> {
    fun enqueue(element: E)

    fun dequeue(): Result<E>

    val front: Result<E>

    val size: Int
}

interface Stack<E> : MutableIterable<E> {
    fun push(element: E)

    fun pop(): Result<E>

    val top: Result<E>

    val size: Int
}

interface Deque<E> : Queue<E>, Stack<E> {
    fun addHead(element: E)

    fun addTail(element: E)

    fun removeHead(): Result<E>

    fun removeTail(): Result<E>

    val head: Result<E>

    val tail: Result<E>


    override val size: Int

    override fun enqueue(element: E) {
        addTail(element)
    }

    override fun dequeue(): Result<E> = removeHead()

    override val front: Result<E>
        get() = head

    override fun push(element: E) {
        addTail(element)
    }

    override fun pop(): Result<E> = removeTail()

    override val top: Result<E>
        get() = tail
}