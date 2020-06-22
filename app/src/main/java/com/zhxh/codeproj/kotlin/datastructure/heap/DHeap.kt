package com.zhxh.codeproj.kotlin.datastructure.heap

import com.zhxh.codeproj.kotlin.datastructure.heap.BinaryHeap.Companion.DEFAULT_INITIAL_CAPACITY
import java.util.ConcurrentModificationException

/**
 * d-堆
 * 与二叉堆类似，区别在于每个节点有 d 个子节点（d >= 2）
 * 二叉堆即 2-堆
 *
 * @param initialCapacity 初始化数组的大小, 默认为 [DEFAULT_INITIAL_CAPACITY]
 * @param fromArray 构建二叉堆的数组，默认为 null
 * @param comparator 堆内元素的比较器，默认为 null
 *
 * @throws IllegalArgumentException 如果 `D < 2 || initialCapacity < 0`
 */
class DHeap<E>(
        val D: Int,
        initialCapacity: Int = DEFAULT_INITIAL_CAPACITY,
        fromArray: Array<out E>? = null,
        override val comparator: Comparator<in E>? = null
) : Heap<E> {

    private var data: Array<Any?>

    private var _size: Int = fromArray?.size ?: 0

    private var modCount = 0

    init {
        require(D >= 2) { "D < 2: $D" }
        require(initialCapacity >= 0) {
            "Negative initial capacity: $initialCapacity"
        }

        @Suppress("UNCHECKED_CAST")
        data = when {
            fromArray == null -> arrayOfNulls(initialCapacity)
            fromArray.size >= initialCapacity -> fromArray as Array<Any?>
            else -> fromArray.copyOf(initialCapacity) as Array<Any?>
        }

        if (_size > 1) {
            for (i in ((_size - 2) / D) downTo 0) {
                percolateDown(i)
            }
        }
    }

    override val size: Int
        get() = _size

    override fun add(element: E) {
        if (_size >= data.size) expandArray()

        data[_size] = element
        _size++
        modCount++
        percolateUp(_size - 1)
    }

    override fun remove(): E? {
        if (_size == 0) return null
        return removeAt(0)
    }

    override fun get(): E? {
        if (_size == 0) return null
        return dataAt(0)
    }

    override fun remove(element: E): Boolean {
        val index = data.indexOf(element)
        if (index !in 0..(_size - 1)) return false
        removeAt(index)
        return true
    }

    override fun iterator() = object : Iterator<E> {
        private var index = 0
        private val bound = _size
        private val expectedModCount = modCount

        override fun hasNext() = index < bound

        override fun next(): E {
            if (index >= bound) throw NoSuchElementException()
            if (expectedModCount != modCount) {
                throw ConcurrentModificationException()
            }

            return dataAt(index++)
        }
    }

    private val Int.parent: Int?
        get() = ((this - 1) / D).takeIf { this in 1..(_size - 1) }

    private val Int.children: IntRange
        get() = (this * D + 1)..minOf(_size - 1, (this + 1) * D)

    private val Int.minChild: Int?
        get() {
            val children = this.children
            if (children.isEmpty()) return null
            return children.fold(children.start) { minIndex, currentIndex ->
                if (dataAt(minIndex) cmp dataAt(currentIndex) > 0) currentIndex else minIndex
            }
        }

    private tailrec fun percolateUp(index: Int) {
        val parent = index.parent
        if (parent != null && dataAt(parent) cmp dataAt(index) > 0) {
            swap(index, parent)
            percolateUp(parent)
        }
    }

    private tailrec fun percolateDown(index: Int) {
        val minChild = index.minChild
        if (minChild != null && dataAt(minChild) cmp dataAt(index) < 0) {
            swap(index, minChild)
            percolateDown(minChild)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun dataAt(index: Int) = data[index] as E

    @Suppress("UNCHECKED_CAST")
    private infix fun E.cmp(that: E): Int {
        return comparator?.compare(this, that) ?: (this as Comparable<E>).compareTo(that)
    }

    private fun swap(indexA: Int, indexB: Int) {
        val temp = data[indexA]
        data[indexA] = data[indexB]
        data[indexB] = temp
    }

    private fun expandArray() {
        val oldSize = data.size
        val newSize = maxOf(oldSize + oldSize / 2, DEFAULT_INITIAL_CAPACITY)
        if (newSize < 0) throw OutOfMemoryError()
        data = data.copyOf(newSize)
    }

    private fun removeAt(index: Int): E {
        val element = dataAt(index)
        _size--
        modCount++
        if (index == _size) {
            data[_size] = null
        } else {
            data[index] = data[_size]
            data[_size] = null
            percolateDown(index)
        }
        return element
    }
}