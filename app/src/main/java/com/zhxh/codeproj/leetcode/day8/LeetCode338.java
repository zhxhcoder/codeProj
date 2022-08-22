package com.zhxh.codeproj.leetcode.day8;

import java.util.Arrays;

/*
给你一个整数 n ，对于0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。



示例 1：

输入：n = 2
输出：[0,1,1]
解释：
0 --> 0
1 --> 1
2 --> 10
示例 2：

输入：n = 5
输出：[0,1,1,2,1,2]
解释：
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101


提示：

0 <= n <= 105


进阶：

很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
你能不使用任何内置函数解决此问题吗？（如，C++ 中的__builtin_popcount ）

 */
public class LeetCode338 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().countBits(3)));
        System.out.println(Arrays.toString(new Solution2().countBits(3)));
        System.out.println(Arrays.toString(new Solution3().countBits(3)));
        System.out.println(Arrays.toString(new Solution4().countBits(3)));
    }

    /*
    动态规划-最高有效位
     */
    static class Solution {
        public int[] countBits(int n) {
            int[] bits = new int[n + 1];
            int highBit = 0;
            for (int i = 1; i <= n; i++) {
                if ((i & (i - 1)) == 0) {
                    highBit = i;
                }
                bits[i] = bits[i - highBit] + 1;
            }
            return bits;
        }
    }

    static class Solution2 {
        public int[] countBits(int n) {
            int[] bits = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                bits[i] = bits[i >> 1] + (i & 1);
            }
            return bits;
        }
    }

    static class Solution3 {
        public int[] countBits(int n) {
            int[] bits = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                bits[i] = bits[i & (i - 1)] + 1;
            }
            return bits;
        }
    }

    static class Solution4 {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            int i = 0, b = 1;
            // [0, b) 被计算
            while (b <= num) {
                // 从 [0, b) 生成 [b, 2b) 或 [b, num)
                while (i < b && i + b <= num) {
                    ans[i + b] = ans[i] + 1;
                    ++i;
                }
                i = 0;   // 重置 i
                b <<= 1; // b = 2b
            }
            return ans;
        }
    }
}
