package com.zhxh.codeproj.kotlin.datastructure.heap

import com.zhxh.codeproj.kotlin.datastructure.heap.BinaryHeap.Companion.DEFAULT_INITIAL_CAPACITY
import java.util.ConcurrentModificationException

/**
 * 二叉堆
 *
 * 内部使用数组实现，所有元素按层存储于数组内
 * 通过计算索引访问父节点和左右子节点
 *
 * @param initialCapacity 初始化数组的大小, 默认为 [DEFAULT_INITIAL_CAPACITY]
 * @param fromArray 构建二叉堆的数组，默认为 null
 * @param comparator 堆内元素的比较器，默认为 null
 *
 * @throws IllegalArgumentException 如果 `initialCapacity < 0`
 */
class BinaryHeap<E>(
    initialCapacity: Int = DEFAULT_INITIAL_CAPACITY,
    fromArray: Array<out E>? = null,
    override val comparator: Comparator<in E>? = null
) : Heap<E> {

    private var data: Array<Any?>

    private var _size: Int = fromArray?.size ?: 0

    private var modCount = 0

    init {
        require(initialCapacity >= 0) {
            "Negative initial capacity: $initialCapacity"
        }

        @Suppress("UNCHECKED_CAST")
        data = when {
            fromArray == null -> arrayOfNulls(initialCapacity)
            fromArray.size >= initialCapacity -> fromArray as Array<Any?>
            else -> fromArray.copyOf(initialCapacity) as Array<Any?>
        }

        // 从下到上地对所有非叶节点（有子节点的节点）执行下滤操作
        // 操作过程中，较小的元素会逐步上浮（接近根节点），较大的元素则逐步沉下
        // 操作完成后，最小的元素上浮为根节点（索引为 0），所有节点满足堆的要求
        if (_size > 1) {
            for (i in (_size / 2 - 1) downTo 0) {
                percolateDown(i)
            }
        }
    }


    override val size: Int
        get() = _size

    // 首先在堆尾添加元素，
    // 因为堆的后半段都是较大的元素，而新增元素可能是个较小的元素，
    // 所以对这个节点执行上滤操作，使其上浮到合适的位置
    override fun add(element: E) {
        if (_size >= data.size) expandArray()
        data[_size] = element
        _size++
        modCount++
        percolateUp(_size - 1)
    }

    // 移除根节点时，使用堆尾的元素替代根节点元素，
    // 堆尾的元素一定是个较大的元素（大于一半的堆元素），而根节点应当是最小的元素，
    // 所以对替换后的根节点执行下滤操作，使其下沉到合适的位置
    override fun remove(): E? {
        if (_size <= 0) return null
        return removeAt(0)
    }

    override fun get(): E? {
        if (_size <= 0) return null
        return dataAt(0)
    }

    /**
     * 二叉堆的迭代器，按照内部数组的顺序返回堆内元素
     */
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

    override fun remove(element: E): Boolean {
        val index = data.indexOf(element)
        if (index < 0) return false
        removeAt(index)
        return true
    }

    companion object {
        internal const val DEFAULT_INITIAL_CAPACITY = 10
    }

    // 通过节点的索引取父节点索引
    // `0.parent = null`
    private inline val Int.parent: Int?
        get() = ((this - 1) / 2).takeIf { this in 1..(_size - 1) }

    // 通过节点的索引取左子节点索引
    // 如果 `(this * 2 + 1) >= size`，说明该节点没有左子节点（更确切地说是没有子节点），因此返回 null
    private inline val Int.left: Int?
        get() = (this * 2 + 1).takeIf { it in 0..(_size - 1) }

    // 通过节点的索引取右子节点索引
    // 如果 `(this * 2 + 2) >= size`，说明该节点没有右子节点，因此返回 null
    private inline val Int.right: Int?
        get() = (this * 2 + 2).takeIf { it in 0..(_size - 1) }

    @Suppress("UNCHECKED_CAST")
    private fun dataAt(index: Int) = data[index] as E

    // 如果 `comparator != null`，则使用 comparator 比较元素大小
    // 如果 `comparator == null`，则使用默认顺序比较
    // 如果 `E !is Comparable<E>`，则抛出 ClassCastException
    @Suppress("UNCHECKED_CAST")
    private infix fun E.cmp(that: E): Int {
        return comparator?.compare(this, that) ?:
            (this as Comparable<E>).compareTo(that)
    }

    private fun swap(indexA: Int, indexB: Int) {
        val temp = data[indexA]
        data[indexA] = data[indexB]
        data[indexB] = temp
    }

    // 使节点向上浮动至合适的位置
    //
    // 对于一个需要向上浮动的节点，只需要比较它与父节点的大小即可
    // 如果父节点的元素较大，则交换两个节点的元素，再对父节点执行同样的操作
    // 如此往复，直到上浮的节点成为根节点，或者父节点的元素小于这个节点为止
    private tailrec fun percolateUp(index: Int) {
        val parent = index.parent

        if (parent != null && dataAt(index) cmp dataAt(parent) < 0) {
            swap(index, parent)
            percolateUp(parent)
        }
    }

    // 使节点下沉到合适的位置
    //
    // 下沉节点的操作比较复杂，因为需要处理子节点的三种情况：
    // 1. 如果 `left == null`，则不存在子节点，该节点已无法下滤，结束操作
    // 2. 如果 `right == null`，则只存在一个左子节点，比较两个节点的元素，
    //    如果左子节点小于该节点，则交换两个节点的元素，接着对左子节点进行下滤操作，否则结束操作
    // 3. 如果 `left != null && right != null`，需要先比较出较小的子节点，接着比较该节点与较小的子节点，
    //    如果该节点大于这个子节点，则交换两个节点的元素，再对较小的节点进行下滤操作，否则结束操作
    private tailrec fun percolateDown(index: Int) {
        val left = index.left
        val right = index.right

        when {
            left == null -> return

            right == null -> if (dataAt(index) cmp dataAt(left) > 0) {
                swap(index, left)
                percolateDown(left)
            }

            else -> {
                val dataParent = dataAt(index)
                val dataLeft = dataAt(left)
                val dataRight = dataAt(right)
                val cmpLeftRight = dataLeft cmp dataRight

                if (cmpLeftRight <= 0 && dataParent cmp dataLeft > 0) {
                    swap(index, left)
                    percolateDown(left)
                } else if (cmpLeftRight > 0 && dataParent cmp dataRight > 0) {
                    swap(index, right)
                    percolateDown(right)
                }
            }
        }
    }

    // 每次将 data 数组扩展到原来的 1.5 倍大小
    // 第一次调用该函数后，data 数组的长度至少为 DEFAULT_INITIAL_CAPACITY
    private fun expandArray() {
        val oldSize = data.size
        val newSize = maxOf(oldSize + oldSize / 2, DEFAULT_INITIAL_CAPACITY)
        if (newSize < 0) throw OutOfMemoryError()
        data = data.copyOf(newSize)
    }

    // 删除 data 数组中特定索引位置的元素
    private fun removeAt(index: Int): E {
//        assert(index in 0 until _size)
        val element = dataAt(index)
        if (index == _size - 1) {
            data[index] = null
            _size--
            modCount++
        } else {
            data[index] = data[_size - 1]
            data[_size - 1] = null
            _size--
            modCount++
            percolateDown(index)
        }
        return element
    }
}