package com.zhxh.codeproj.leetcode.ace100.design;

import java.util.Stack;

/*
155. 最小栈
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

实现 MinStack 类:

MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。


示例 1:

输入：
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

输出：
[null,null,null,null,-3,null,0,-2]

解释：
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.


提示：

-231 <= val <= 231 - 1
pop、top 和 getMin 操作总是在 非空栈 上调用
push, pop, top, and getMin最多被调用 3 * 104 次
 */

public class LeetCode155 {
    public static void main(String[] args) {
        //todo 设计类
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }

    /*
    方法一：辅助栈
     */
    static class MinStack {
        // 数据栈
        private Stack<Integer> data;
        // 辅助栈
        private Stack<Integer> helper;

        /*
         在这里初始化你的数据结构。
         */
        public MinStack() {
            data = new Stack<>();
            helper = new Stack<>();
        }

        // 思路 1：数据栈和辅助栈在任何时候都同步
        public void push(int x) {
            // 数据栈和辅助栈一定会增加元素
            data.add(x);
            if (helper.isEmpty() || helper.peek() >= x) {
                helper.add(x);
            } else {
                helper.add(helper.peek());
            }
        }

        public void pop() {
            // 两个栈都得 pop
            if (!data.isEmpty()) {
                helper.pop();
                data.pop();
            }
        }

        public int top() {
            if (!data.isEmpty()) {
                return data.peek();
            }
            throw new RuntimeException("栈中元素为空，此操作非法");
        }

        public int getMin() {
            if (!helper.isEmpty()) {
                return helper.peek();
            }
            throw new RuntimeException("栈中元素为空，此操作非法");
        }
    }
}
