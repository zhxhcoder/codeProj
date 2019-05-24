package cnife.datastructure.tree

import cnife.datastructure.tree.BinarySortedTree.Node

/**
 * 获得指定节点的最小子节点
 *
 * @return 当且仅当左子节点不存在时返回 `null`
 */
val <E> Node<E>.minChild: Node<E>?
    get() {
        var n = this
        var l: Node<E>? = left ?: return null
        while (l != null) {
            n = l
            l = l.left
        }
        return n
    }

/**
 * 获得指定节点的最大子节点
 *
 * @return 当且仅当右子节点不存在时返回 `null`
 */
val <E> Node<E>.maxChild: Node<E>?
    get() {
        var n = this
        var r: Node<E>? = right ?: return null
        while (r != null) {
            n = r
            r = r.right
        }
        return n
    }

/**
 * 获得指定节点的前继节点
 *
 * @return 当且仅当节点是所在树的最小子节点时，返回 `null`
 */
val <E> Node<E>.prevNode: Node<E>?
    get() = left?.maxChild ?: let {
        var n = this
        var p = parent
        while (p != null && n == p.left) {
            n = p
            p = p.parent
        }
        p
    }

/**
 * 获得指定节点的后继节点
 *
 * @return 当且仅当节点是所在树的最大子节点时，返回 `null`
 */
val <E> Node<E>.nextNode: Node<E>?
    get() = right?.minChild ?: let {
        var n = this
        var p = parent
        while (p != null && n == p.right) {
            n = p
            p = p.parent
        }
        p
    }
