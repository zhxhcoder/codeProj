package com.zhxh.codeproj.leetcode.hot100.day3;

import java.util.Arrays;

/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
问总共有多少条不同的路径？
例如，上图是一个7 x 3 的网格。有多少可能的路径？

示例1:

输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
示例2:

输入: m = 7, n = 3
输出: 28

提示：

1 <= m, n <= 100
题目数据保证答案小于等于 2 * 10 ^ 9


 */
class LeetCode62 {
    public static void main(String[] args) {
        //todo 动态规划-dp
        System.out.println(new Solution().uniquePaths(9, 8));
        System.out.println(new Solution2().uniquePaths(9, 8));
        System.out.println(new Solution3().uniquePaths(9, 8));
        System.out.println(new Solution4().uniquePaths(9, 8));
    }
    /*
       思路
       思路一：排列组合

       因为机器到底右下角，向下几步，向右几步都是固定的，

       比如，m=3, n=2，我们只要向下 1 步，向右 2 步就一定能到达终点。

       所以有 C_{m+n-2}^{m-1}C
       m+n−2
       m−1

       Python

       def uniquePaths(self, m: int, n: int) -> int:
               return int(math.factorial(m+n-2)/math.factorial(m-1)/math.factorial(n-1))

     */

    static class Solution {
        public int uniquePaths(int m, int n) {
            int[] cur = new int[n];
            Arrays.fill(cur, 1);
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    cur[j] += cur[j - 1];
                }
            }
            return cur[n - 1];
        }
    }

    static class Solution2 {
        /*
       思路二：动态规划
       我们令 dp[i][j] 是到达 i, j 最多路径
       都有上一个和左一个决定
       动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
       注意，对于第一行 dp[0][j]，或者第一列 dp[i][0]，由于都是在边界，所以只能为 1

       时间复杂度：O(m*n)
       空间复杂度：O(m*n)

       优化：因为我们每次只需要 dp[i-1][j],dp[i][j-1]
       所以我们只要记录这两个数，直接看代码吧！
         */
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            for (int i = 0; i < n; i++) dp[0][i] = 1;
            for (int i = 0; i < m; i++) dp[i][0] = 1;
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m - 1][n - 1];
        }
    }

    static class Solution3 {
        public int uniquePaths(int m, int n) {
            int[] pre = new int[n];
            int[] cur = new int[n];
            Arrays.fill(pre, 1);
            Arrays.fill(cur, 1);
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    cur[j] = cur[j - 1] + pre[j];
                }
                pre = cur.clone();
            }
            return pre[n - 1];
        }
    }

    static class Solution4 {
        public int uniquePaths(int m, int n) {
            int[] cur = new int[n];
            Arrays.fill(cur, 1);
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    cur[j] += cur[j - 1];
                }
            }
            return cur[n - 1];
        }
    }
}
