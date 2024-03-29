package com.zhxh.codeproj.leetcode.top145;

import java.util.HashSet;
import java.util.Set;

/*
编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为 1，那么这个数就是快乐数。

如果 n 是快乐数就返回 True ；不是，则返回 False 。

示例：

输入：19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

 */
public class LeetCode202 {
    public static void main(String[] args) {
        System.out.println(new Solution().isHappy(19));
        System.out.println(new Solution2().isHappy(19));
        System.out.println(new Solution3().isHappy(19));
    }

    static class Solution {
        public boolean isHappy(int n) {
            Set<Integer> set = new HashSet<>();
            int m = 0;
            while (true) {
                while (n != 0) {
                    m += Math.pow(n % 10, 2);
                    n /= 10;
                }
                if (m == 1) {
                    return true;
                }
                if (set.contains(m)) {
                    return false;
                } else {
                    set.add(m);
                    n = m;
                    m = 0;
                }
            }
        }
    }

    /*
    方法一：用哈希集合检测循环
     */
    static class Solution2 {
        private int getNext(int n) {
            int totalSum = 0;
            while (n > 0) {
                int d = n % 10;
                n = n / 10;
                totalSum += d * d;
            }
            return totalSum;
        }

        public boolean isHappy(int n) {
            Set<Integer> seen = new HashSet<>();
            while (n != 1 && !seen.contains(n)) {
                seen.add(n);
                n = getNext(n);
            }
            return n == 1;
        }
    }

    /*
    方法二：快慢指针法
     */
    static class Solution3 {
        public int getNext(int n) {
            int totalSum = 0;
            while (n > 0) {
                int d = n % 10;
                n = n / 10;
                totalSum += d * d;
            }
            return totalSum;
        }

        public boolean isHappy(int n) {
            int slowRunner = n;
            int fastRunner = getNext(n);
            while (fastRunner != 1 && slowRunner != fastRunner) {
                slowRunner = getNext(slowRunner);
                fastRunner = getNext(getNext(fastRunner));
            }
            return fastRunner == 1;
        }
    }
}
