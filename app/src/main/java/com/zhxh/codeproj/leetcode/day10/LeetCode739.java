package com.zhxh.codeproj.leetcode.day10;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/*
给定一个整数数组temperatures，表示每天的温度，返回一个数组answer，其中answer[i]是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用0 来代替。



示例 1:

输入: temperatures = [73,74,75,71,69,72,76,73]
输出:[1,1,4,2,1,1,0,0]
示例 2:

输入: temperatures = [30,40,50,60]
输出:[1,1,1,0]
示例 3:

输入: temperatures = [30,60,90]
输出: [1,1,0]


提示：

1 <=temperatures.length <= 105
30 <=temperatures[i]<= 100

 */
class LeetCode739 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println(Arrays.toString(new Solution2().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println(Arrays.toString(new Solution3().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

    /*
    暴力
     */
    static class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int length = temperatures.length;
            int[] ans = new int[length];
            int[] next = new int[101];
            Arrays.fill(next, Integer.MAX_VALUE);
            for (int i = length - 1; i >= 0; --i) {
                int warmerIndex = Integer.MAX_VALUE;
                for (int t = temperatures[i] + 1; t <= 100; ++t) {
                    if (next[t] < warmerIndex) {
                        warmerIndex = next[t];
                    }
                }
                if (warmerIndex < Integer.MAX_VALUE) {
                    ans[i] = warmerIndex - i;
                }
                next[temperatures[i]] = i;
            }
            return ans;
        }
    }
    /*
    输入: temperatures = [73,74,75,71,69,72,76,73]
    单调栈
    可以维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。
    如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。
     */

    static class Solution2 {
        public int[] dailyTemperatures(int[] temperatures) {
            int length = temperatures.length;
            //存储结果
            int[] ans = new int[length];
            //双端队列，作为队列时，add和remove方法，作为栈时，push和pop
            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < length; i++) {
                int temperature = temperatures[i];
                //当栈不为空，且 当前温度大于栈顶温度
                while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                    //匹配成功，出栈并赋值
                    int prevIndex = stack.pop();
                    ans[prevIndex] = i - prevIndex;
                }
                //因为判断需要以后的元素，所以最后入栈
                stack.push(i);
            }
            return ans;
        }
    }

    static class Solution3 {
        public int[] dailyTemperatures(int[] T) {
            int[] ans = new int[T.length];
            Stack<Integer> stack = new Stack();
            for (int i = T.length - 1; i >= 0; --i) {
                while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
                ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
                stack.push(i);
            }
            return ans;
        }
    }
}

