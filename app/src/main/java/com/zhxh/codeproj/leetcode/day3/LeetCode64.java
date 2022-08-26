package com.zhxh.codeproj.leetcode.day3;

/*
给定一个包含非负整数的 mxn网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
 [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。

 */
public class LeetCode64 {
    public static void main(String[] args) {
        //todo 动态规划-dp
        System.out.println(new Solution().minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
        System.out.println(new Solution2().minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
        System.out.println(new Solution3().minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
        System.out.println(new Solution4().minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        }));
    }

    /*
    思路分析：
    -很明显，我们在遇到这样的统计可行路径的数量，或者求最小路径的时候，比较容易想到的两种做法，一个是搜索，另一个是动态规划
    -而搜索的做法，仅仅在数据规模比较小的时候才考虑使用，所以在这里，我们尝试采用动态规划来解决这个问题。

    动态规划，主要关注以下两点
    1，状态的设置。在这个题目里，由于要求最小路径和，我们可以令dp[i][j]代表从（i,j）走到（m-1,n-1）点的最小路径和
    2，状态转移方程。我们考虑如何来求出dp[i][j]。由于每次只能往右或下走，所以(i,j)只能走到(i+1,j)或者(i,j+1)。
       换言之，dp[i][j]的前续状态只有dp[i+1][j],dp[i][j+1],所以我们在两者取最小，然后加上这个格子内的数即可。
       dp(i,j)=grid(i,j)+min(dp(i+1,j),dp(i,j+1))

     */
    static class Solution {
        public int minPathSum(int[][] grid) {
            int[][] dp = new int[grid.length][grid[0].length];
            for (int i = grid.length - 1; i >= 0; i--) {
                for (int j = grid[0].length - 1; j >= 0; j--) {
                    if (i == grid.length - 1 && j != grid[0].length - 1)
                        dp[i][j] = grid[i][j] + dp[i][j + 1];
                    else if (j == grid[0].length - 1 && i != grid.length - 1)
                        dp[i][j] = grid[i][j] + dp[i + 1][j];
                    else if (j != grid[0].length - 1 && i != grid.length - 1)
                        dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                    else
                        dp[i][j] = grid[i][j];
                }
            }
            return dp[0][0];
        }
    }

    /*
     动态规划-优化方法滚动数组
     我们可以用一个一维数组来代替二维数组，dp数组的大小和行大小相同。
     这是因为对于某个固定状态，只需要考虑下方和右侧的节点。
     我们就可以一行一行计算，来节省空间复杂度。
      */
    static class Solution2 {
        public int minPathSum(int[][] grid) {
            int[] dp = new int[grid[0].length];
            for (int i = grid.length - 1; i >= 0; i--) {
                for (int j = grid[0].length - 1; j >= 0; j--) {
                    if (i == grid.length - 1 && j != grid[0].length - 1) {
                        dp[j] = grid[i][j] + dp[j + 1];
                    } else if (j == grid[0].length - 1 && i != grid.length - 1) {
                        dp[j] = grid[i][j] + dp[j];
                    } else if (j != grid[0].length - 1 && i != grid.length - 1) {
                        dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                    } else {
                        dp[j] = grid[i][j];
                    }
                }
            }
            return dp[0];
        }
    }

    /*模仿LeetCode 62题目
     同样只能向下和向右走
     动态方程：dp[i][j] = Math.min( dp[i-1][j] + dp[i][j-1])
    */
    static class Solution3 {
        public int minPathSum(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int[][] dp = new int[m][n];
            //从后向前
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (i == m - 1 && j != n - 1) {//下边界
                        dp[i][j] = grid[i][j] + dp[i][j + 1];
                    } else if (j == n - 1 && i != m - 1) {//右边界
                        dp[i][j] = grid[i][j] + dp[i + 1][j];
                    } else if (j != n - 1 && i != m - 1) {//中间地带
                        dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                    } else {
                        dp[i][j] = grid[i][j];
                    }
                }
            }
            return dp[0][0];
        }
    }

    /*
    一维数组
    空间优化
     */
    static class Solution4 {
        public int minPathSum(int[][] grid) {
            int len = grid[0].length;
            int[] dp = new int[len];
            dp[0] = grid[0][0];
            for (int i = 1; i < len; i++) {
                dp[i] = dp[i - 1] + grid[0][i];
            }
            for (int i = 1; i < grid.length; i++) {
                dp[0] = dp[0] + grid[i][0];
                for (int j = 1; j < len; j++) {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
            return dp[len - 1];
        }
    }
}
