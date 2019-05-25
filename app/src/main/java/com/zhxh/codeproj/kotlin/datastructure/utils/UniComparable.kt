package com.zhxh.codeproj.kotlin.datastructure.utils

/**
 * 通用的比较器
 *
 * 提供一个 [cmp] 函数来比较两个元素的大小
 *
 * @property comparator 如果不为 `null`，则使用它来比较两个元素；如果为 `null`，则使用自然顺序比较两个元素
 */
interface UniComparable<in E> {

    val comparator: Comparator<in E>?

    /**
     * 根据 [comparator] 来比较两个对象
     *
     * @receiver 第一个值
     * @param that 第二个值
     * @return 比较值 n：
     *         - `this < that` -> n < 0
     *         - `this > that` -> n > 0
     *         - `this == that` -> n == 0
     *
     * @throws ClassCastException 如果 `[comparator] == null` 且 `E !is [Comparable]<E>`
     */
    @Suppress("UNCHECKED_CAST")
    infix fun E.cmp(that: E): Int =
        comparator?.compare(this, that) ?: (this as Comparable<E>).compareTo(that)
}