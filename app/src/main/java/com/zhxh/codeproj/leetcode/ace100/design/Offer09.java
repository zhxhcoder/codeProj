package com.zhxh.codeproj.leetcode.ace100.design;

import java.util.ArrayDeque;
import java.util.Deque;

/*
剑指 Offer 09. 用两个栈实现队列
用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

示例 1：

输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
示例 2：

输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
提示：

1 <= values <= 10000
最多会对 appendTail、deleteHead 进行 10000 次调用
 */
public class Offer09 {
    public static void main(String[] args) {
        //todo 设计类
        CQueue obj = new CQueue();
        obj.appendTail(1);
        obj.appendTail(2);
        obj.deleteHead();
    }

    /*
    将一个栈当作输入栈，用于压入appendTail传入的数据；
    另一个栈当作输出栈，用于deleteHead操作。
    每次deleteHead时，若输出栈为空则将输入栈的全部数据依次弹出并压入输出栈，
    这样输出栈从栈顶往栈底的顺序就是队列从队首往队尾的顺序
     */
    static class CQueue {
        Deque<Integer> inStack;
        Deque<Integer> outStack;

        public CQueue() {
            inStack = new ArrayDeque<Integer>();
            outStack = new ArrayDeque<Integer>();
        }

        public void appendTail(int value) {
            inStack.push(value);
        }

        public int deleteHead() {
            if (outStack.isEmpty()) {
                if (inStack.isEmpty()) {
                    return -1;
                }
                //倒腾一下
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }
    }
}
