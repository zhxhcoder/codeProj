package cnife.datastructure.heap

/**
 * 堆
 *
 * 堆的每个节点的值都小于它的子节点值
 */
interface Heap<E> : Iterable<E> {

    /**
     * 向堆中添加元素
     *
     * @throws ClassCastException 如果 `[comparator] == null && E !is Comparable<E>`
     */
    fun add(element: E)

    /**
     * 获取并删除堆内最小的节点
     *
     * @return null 如果 `[size] == 0`
     * @return 堆内最小的元素
     * @throws ClassCastException 如果 `[comparator] == null && E !is Comparable<E>`
     */
    fun remove(): E?

    /**
     * 获取但不删除堆内最小的节点
     *
     * @return null 如果 `[size] == 0`
     * @return 堆内最小的元素
     * @throws ClassCastException 如果 `[comparator] == null && E !is Comparable<E>`
     */
    fun get(): E?

    /**
     * 获取并删除堆内最小的节点
     * 在堆非空时与 [Heap.remove] 等同
     *
     * @return 堆内最小的元素
     * @throws NoSuchElementException 如果 `[size] == 0`
     * @throws ClassCastException 如果 `[comparator] == null && E !is Comparable<E>`
     */
    fun pop(): E = remove() ?: throw NoSuchElementException()

    /**
     * 获取但不删除堆内最小的节点
     * 在堆非空时与 [Heap.get] 等同
     *
     * @return 堆内最小的元素
     * @throws NoSuchElementException 如果 `[size] == 0`
     * @throws ClassCastException 如果 `[comparator] == null && E !is Comparable<E>`
     */
    fun peek(): E = get() ?: throw NoSuchElementException()

    /**
     * 删除首个与 [element] 相等的元素
     *
     * @return true 如果存在与 [element] 相等的元素并删除成功
     * @return false 如果不存在与 [element] 相等的元素或删除失败
     * @throws UnsupportedOperationException 如果不支持该操作
     */
    fun remove(element: E): Boolean = throw UnsupportedOperationException()

    /**
     * 堆的大小
     */
    val size: Int

    /**
     * 堆内元素的比较顺序
     *
     * @return null 如果使用默认比较器
     */
    val comparator: Comparator<in E>?
}