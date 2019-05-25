package com.zhxh.codeproj.kotlin.datastructure

import com.zhxh.codeproj.kotlin.datastructure.utils.Result

interface Collection<out E> : Iterable<E> {
    val size: Int

    val iterator: Iterator<E>
        get() = iterator()

    operator fun contains(element: @UnsafeVariance E): Boolean
}

interface MutableCollection<E> : Collection<E>, MutableIterable<E> {
    fun add(element: E): Boolean

    fun addAll(elements: Collection<E>): Boolean

    operator fun plusAssign(element: E) {
        add(element)
    }

    operator fun plusAssign(elements: Collection<E>) {
        addAll(elements)
    }

    fun remove(element: E): Result<E>

    fun removeAll(elements: Collection<E>): Boolean

    operator fun minusAssign(element: E) {
        remove(element)
    }

    operator fun minusAssign(elements: Collection<E>) {
        removeAll(elements)
    }

    fun clear()

    override fun iterator(): MutableIterator<E>
}

interface Set<out E> : Collection<E>

interface MutableSet<E> : Set<E>, MutableCollection<E>