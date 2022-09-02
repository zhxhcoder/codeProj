package com.zhxh.codeproj.leetcode.top145;

import java.util.*;

/*
给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。

实现扁平迭代器类 NestedIterator ：

NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
int next() 返回嵌套列表的下一个整数。
boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
你的代码将会用下述伪代码检测：

initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res
如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。



示例 1：

输入：nestedList = [[1,1],2,[1,1]]
输出：[1,1,2,1,1]
解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
示例 2：

输入：nestedList = [1,[4,[6]]]
输出：[1,4,6]
解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。


提示：

1 <= nestedList.length <= 500
嵌套列表中的整数值在范围 [-106, 106] 内

 */
public class LeetCode341 {
    public static void main(String[] args) {
    }

    /*
    方法一：深度优先搜索
     */
    static class NestedIterator implements Iterator<Integer> {
        private List<Integer> vals;
        private Iterator<Integer> cur;

        public NestedIterator(List<NestedInteger> nestedList) {
            vals = new ArrayList<Integer>();
            dfs(nestedList);
            cur = vals.iterator();
        }

        @Override
        public Integer next() {
            return cur.next();
        }

        @Override
        public boolean hasNext() {
            return cur.hasNext();
        }

        private void dfs(List<NestedInteger> nestedList) {
            for (NestedInteger nest : nestedList) {
                if (nest.isInteger()) {
                    vals.add(nest.getInteger());
                } else {
                    dfs(nest.getList());
                }
            }
        }
    }

    public interface NestedInteger {
        // @return true 如果此 NestedInteger 包含单个整数，而不是嵌套列表。
        public boolean isInteger();

        // @return 此 NestedInteger 包含的单个整数（如果它包含单个整数）
        // 返回 null 如果此 NestedInteger 包含一个嵌套列表
        public Integer getInteger();

        // @return 此 NestedInteger 包含的嵌套列表（如果它包含嵌套列表）
        // 返回 空列表 如果此 NestedInteger 包含单个整数
        public List<NestedInteger> getList();
    }

    /*
    栈
     */
    public class NestedIterator2 implements Iterator<Integer> {
        // 存储列表的当前遍历位置
        private Deque<Iterator<NestedInteger>> stack;

        public NestedIterator2(List<NestedInteger> nestedList) {
            stack = new LinkedList<Iterator<NestedInteger>>();
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            // 由于保证调用 next 之前会调用 hasNext，直接返回栈顶列表的当前元素
            return stack.peek().next().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                Iterator<NestedInteger> it = stack.peek();
                if (!it.hasNext()) { // 遍历到当前列表末尾，出栈
                    stack.pop();
                    continue;
                }
                // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
                NestedInteger nest = it.next();
                if (nest.isInteger()) {
                    List<NestedInteger> list = new ArrayList<NestedInteger>();
                    list.add(nest);
                    stack.push(list.iterator());
                    return true;
                }
                stack.push(nest.getList().iterator());
            }
            return false;
        }
    }

}
