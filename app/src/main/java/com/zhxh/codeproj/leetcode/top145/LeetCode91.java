package com.zhxh.codeproj.leetcode.top145;

/*
一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：

"AAJF" ，将消息分组为 (1 1 10 6)
"KJF" ，将消息分组为 (11 10 6)
注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。

给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。

题目数据保证答案肯定是一个 32 位 的整数。

 

示例 1：

输入：s = "12"
输出：2
解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
示例 2：

输入：s = "226"
输出：3
解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
示例 3：

输入：s = "0"
输出：0
解释：没有字符映射到以 0 开头的数字。
含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 

提示：

1 <= s.length <= 100
s 只包含数字，并且可能包含前导零。
 */
public class LeetCode91 {
    public static void main(String[] args) {
        //todo 动态规划
        System.out.println(new Solution().numDecodings("226"));
        System.out.println(new Solution2().numDecodings("226"));
    }

    /*
    方法一：动态规划
    这类求种类数题, 都可以总结为路径规划问题, 可以建模成一个起点和一个终点, 从起点到终点整个过程就算完成一次路径规划，
    一共有多少种路径规划最后答案就有多少种。
    在使用动态规划求解的时候，对路径的终点进行遍历, 就是假设终点先与起点重合, 然后进行状态转移, 直到得到终点达到题目要求的位置。
    比如, 跳台阶问题, 有体力消耗的跳台阶问题, 比如本题就是从索引0到索引length - 1的路径规划问题, 26 是一步完成还是分两步完成。
    这些都是一维空间的路径规划。 自然还可以拓展到二维空间中的路径规划, 如起点在nums[0][0]中, 终点在num[n][m], 都可以用相同的建模思路。
     */
    static class Solution {
        public int numDecodings(String s) {
            int n = s.length();
            int[] f = new int[n + 1];
            f[0] = 1;
            for (int i = 1; i <= n; ++i) {
                if (s.charAt(i - 1) != '0') {
                    f[i] += f[i - 1];
                }
                if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                    f[i] += f[i - 2];
                }
            }
            return f[n];
        }
    }

    /*
    方法一：动态规划
    注意到在状态转移方程中，fi的值仅于 fi-1和fi-2有关，
    因此我们可以使用三个变量进行状态转移，省去数组的空间。
     */
    static class Solution2 {
        public int numDecodings(String s) {
            int n = s.length();
            // a = f[i-2], b = f[i-1], c=f[i]
            int a = 0, b = 1, c = 0;
            for (int i = 1; i <= n; ++i) {
                c = 0;
                if (s.charAt(i - 1) != '0') {
                    c += b;
                }
                if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                    c += a;
                }
                a = b;
                b = c;
            }
            return c;
        }
    }
}
