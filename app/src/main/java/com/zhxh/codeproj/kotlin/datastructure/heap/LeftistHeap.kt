package com.zhxh.codeproj.kotlin.datastructure.heap

import java.util.ArrayDeque
import java.util.Queue

class LeftistHeap<E>(
    fromIterable: Iterable<E> = emptyList(),
    override val comparator: Comparator<in E>? = null
) : Heap<E> {

    private class Node<E>(
        @JvmField var element: E,
        @JvmField var left: Node<E>? = null,
        @JvmField var right: Node<E>? = null,
        @JvmField var _npl: Int = 0
    )

    private var Node<E>?.npl: Int
        get() = this?._npl ?: -1
        set(value) {
            this?._npl = value
        }

    private fun Node<E>.swapChildren() {
        val temp = left
        left = right
        right = temp
    }


    private var root: Node<E>? = null
    private var _size: Int = 0
    private var modCount: Int = 0

    init {
        fromIterable.forEach { add(it) }
    }


    override val size: Int
        get() = _size

    override fun add(element: E) {
        root = merge(root, Node(element))
        _size++
        modCount++
    }

    override fun remove(): E? = root?.let {
        root = merge(it.left, it.right)
        it.left = null
        it.right = null
        _size--
        modCount++
        it.element
    }

    override fun get(): E? = root?.element

    override fun iterator() = object : Iterator<E> {
        private val queue: Queue<Node<E>> =
            ArrayDeque<Node<E>>().apply { root?.let(::add) }

        override fun hasNext() = queue.isNotEmpty()

        override fun next(): E {
            val node = queue.remove()!!
            node.left?.let(queue::add)
            node.right?.let(queue::add)
            return node.element
        }
    }

    infix fun merge(heap: LeftistHeap<E>) {
        if (this === heap) return
        root = merge(root, heap.root)
        heap.root = null
        _size += heap._size
        modCount++
    }

    // 合并两个左式堆
    // 1. 如果有一个堆为空，直接返回另一个堆；
    // 2. 如果两个堆都不为空，则比较两个堆的根节点，合并根较小的节点的右子节点与另一个堆；
    //    1. 如果右子节点的 npl 大于左子节点的 npl，则交换两个子节点
    //    2. 将根节点 npl 更新为右子节点的 npl + 1；
    private fun merge(rootA: Node<E>?, rootB: Node<E>?): Node<E>? {
        if (rootA == null) return rootB
        if (rootB == null) return rootA

        return if (rootA.element cmp rootB.element < 0)
            merge1(rootA, rootB) else merge1(rootB, rootA)
    }

    private fun merge1(
        rootSmaller: Node<E>, rootLarger: Node<E>
    ) = rootSmaller.apply {
        right = merge(right, rootLarger)
        if (left.npl < right.npl) swapChildren()
        npl = right.npl + 1
    }

    @Suppress("UNCHECKED_CAST")
    private infix fun E.cmp(that: E): Int {
        return comparator?.compare(this, that) ?:
            (this as Comparable<E>).compareTo(that)
    }
}