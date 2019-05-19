package com.zhxh.codeproj.leetcode

/**
 * Created by zhxh on 2019/05/19
 */

object LeetCode002 {
    
    private fun addTwoNumbers(l1: ListNode, l2: ListNode): ListNode {
        val dummyHead = ListNode(0)
        var p: ListNode? = l1
        var q: ListNode? = l2
        var curr = dummyHead
        var carry = 0
        while (p != null || q != null) {
            val x = p?.value ?: 0
            val y = q?.value ?: 0
            val sum = carry + x + y
            carry = sum / 10
            curr.next = ListNode(sum % 10)
            curr = curr.next!!
            if (p != null) p = p.next
            if (q != null) q = q.next
        }
        if (carry > 0) {
            curr.next = ListNode(carry)
        }
        return dummyHead.next!!
    }
    
    class ListNode internal constructor(internal var value: Int) {
        internal var next: ListNode? = null
        
        internal operator fun hasNext(): Boolean {
            return next != null
        }
    }
    
    private fun printNodeList(valNode: ListNode) {
        var node = valNode
        println(node.value)
        while (node.hasNext()) {
            println(node.next?.value)
            node = node.next!!
        }
        println("---------------------")
    }
    
    @JvmStatic
    fun main(args: Array<String>) {
        
        val node1 = ListNode(2)
        val node2 = ListNode(3)
        val node3 = ListNode(8)
        val node4 = ListNode(9)
        
        node1.next = node2
        node3.next = node4
        
        printNodeList(addTwoNumbers(node1, node3))
        
    }
}
