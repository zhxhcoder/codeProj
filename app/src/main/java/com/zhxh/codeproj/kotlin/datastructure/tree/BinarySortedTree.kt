package cnife.datastructure.tree

import cnife.datastructure.utils.UniComparable

/**
 * 二叉搜索树
 *
 * 继承 [MutableIterable]，允许在迭代过程中添加、删除和修改元素
 *
 * @property size 树中的节点数量
 * @property root 根节点
 */
interface BinarySortedTree<E> : MutableIterable<E>, UniComparable<E> {

    /**
     * 树节点
     *
     * @property element 节点中的元素
     * @property parent 父节点
     * @property left 左子节点
     * @property right 右子节点
     */
    interface Node<E> {
        val element: E
        val parent: Node<E>?
        val left: Node<E>?
        val right: Node<E>?
    }

    val size: Int

    val root: Node<E>?


    /**
     * 添加元素到树里
     *
     * @param element 要添加的元素
     * @return 插入成功则返回 `true`，失败返回 `false`
     */
    fun add(element: E): Boolean

    /**
     * 移除指定元素的节点
     *
     * @param element 要移除的元素
     * @return 删除成功则返回 `true`，失败返回 `false`
     */
    fun remove(element: E): Boolean

    /**
     * 寻找包含指定元素的节点
     *
     * @param element 要寻找的元素
     * @return 若存在指定的元素，则返回包含该元素的节点，否则返回 `null`
     */
    fun find(element: E): Node<E>? {
        var n = root
        // 每次迭代，比较 element 与节点元素的大小
        // 若 element 小于节点元素，则继续寻找左子节点，大于则寻找右子节点
        // 相等说明找到了目标元素
        loop@ while (n != null) {
            val cmpValue = element cmp n.element
            n = when {
                cmpValue < 0 -> n.left
                cmpValue > 0 -> n.right
                else -> break@loop
            }
        }
        return n
    }

    /**
     * 判断指定元素是否包含在树里
     *
     * @param element 指定的元素
     * @return 若包含指定的元素，则返回 `true`，否则返回 `false`
     */
    operator fun contains(element: E): Boolean {
        return find(element) != null
    }
}