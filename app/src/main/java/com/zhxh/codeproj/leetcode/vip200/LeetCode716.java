package com.zhxh.codeproj.leetcode.vip200;

import java.util.Stack;

/*
716.最大栈

设计一个最大栈，支持 push、pop、top、peekMax 和 popMax 操作。

push(x) -- 将元素 x 压入栈中。
pop() -- 移除栈顶元素并返回这个值。
top() -- 返回栈顶元素。
peekMax() -- 返回栈中最大元素。
popMax() -- 返回栈中最大的元素，并将其删除。如果有多个最大元素，只要删除最靠近栈顶的那个。

样例 1:

MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5

注释:

    -1e7 <= x <= 1e7
    操作次数不会超过 10000。
    当栈为空的时候不会出现后四个操作。
 */
public class LeetCode716 {
    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        stack.top();
        stack.popMax();
        stack.top();
        stack.peekMax();
        stack.pop();
        stack.top();
    }

    static class MaxStack {
        Stack<Integer> stack;
        Stack<Integer> maxStack;

        public MaxStack() {
            stack = new Stack();
            maxStack = new Stack();
        }

        public void push(int x) {
            int max = maxStack.isEmpty() ? x : maxStack.peek();
            maxStack.push(max > x ? max : x);
            stack.push(x);
        }

        public int pop() {
            maxStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        public int popMax() {
            int max = peekMax();
            Stack<Integer> buffer = new Stack();
            while (top() != max) buffer.push(pop());
            pop();
            while (!buffer.isEmpty()) push(buffer.pop());
            return max;
        }
    }
}
