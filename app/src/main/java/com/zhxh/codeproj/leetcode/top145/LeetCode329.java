package com.zhxh.codeproj.leetcode.top145;

import java.util.*;

/*
给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。



示例 1：


输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
输出：4
解释：最长递增路径为 [1, 2, 6, 9]。
示例 2：


输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
输出：4
解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
示例 3：

输入：matrix = [[1]]
输出：1


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1

 */
public class LeetCode329 {
    public static void main(String[] args) {
        System.out.println(new Solution().longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
        System.out.println(new Solution2().longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
        System.out.println(new Solution3().longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
    }

    /*
    方法一：记忆化深度优先搜索
     */
    static class Solution {
        public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int rows, columns;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            rows = matrix.length;
            columns = matrix[0].length;
            int[][] memo = new int[rows][columns];
            int ans = 0;
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    ans = Math.max(ans, dfs(matrix, i, j, memo));
                }
            }
            return ans;
        }

        public int dfs(int[][] matrix, int row, int column, int[][] memo) {
            if (memo[row][column] != 0) {
                return memo[row][column];
            }
            ++memo[row][column];
            for (int[] dir : dirs) {
                int newRow = row + dir[0], newColumn = column + dir[1];
                if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[row][column]) {
                    memo[row][column] = Math.max(memo[row][column], dfs(matrix, newRow, newColumn, memo) + 1);
                }
            }
            return memo[row][column];
        }
    }

    /*
    方法二：拓扑排序
     */
    static class Solution2 {
        public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public int rows, columns;

        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            rows = matrix.length;
            columns = matrix[0].length;
            int[][] outdegrees = new int[rows][columns];
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    for (int[] dir : dirs) {
                        int newRow = i + dir[0], newColumn = j + dir[1];
                        if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[i][j]) {
                            ++outdegrees[i][j];
                        }
                    }
                }
            }
            Queue<int[]> queue = new LinkedList<int[]>();
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    if (outdegrees[i][j] == 0) {
                        queue.offer(new int[]{i, j});
                    }
                }
            }
            int ans = 0;
            while (!queue.isEmpty()) {
                ++ans;
                int size = queue.size();
                for (int i = 0; i < size; ++i) {
                    int[] cell = queue.poll();
                    int row = cell[0], column = cell[1];
                    for (int[] dir : dirs) {
                        int newRow = row + dir[0], newColumn = column + dir[1];
                        if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] < matrix[row][column]) {
                            --outdegrees[newRow][newColumn];
                            if (outdegrees[newRow][newColumn] == 0) {
                                queue.offer(new int[]{newRow, newColumn});
                            }
                        }
                    }
                }
            }
            return ans;
        }
    }

    /*
    动态规划
     */
    static class Solution3 {
        public int longestIncreasingPath(int[][] matrix) {
        /*
        方法1:动态规划
        1.状态定义:dp[i][j]为以matrix[i][j]为起点的最大严格递增路径长度
        2.状态转移:由于matrix[i][j]为起点,因此dp[i][j]可以由其上下左右四个方向计算得到
            取dp[i][j]=Math.max(dp[i][j],dp[r][c]+1),即四个节点为起点的最长路径+1作为dp[i][j]
            为什么dp[r][c]一定就是计算过的?
            因为可以通过遍历顺序来保证比matrix[i][j]大的节点都已经计算过
            而比matrix[i][j]小的节点的dp值对于dp[i][j]来说没用,因此可以放到后面计算
        3.初始化:最短的递增路径长度为1,将dp[i][j]全部初始化为1
        4.遍历顺序:首先将matrix的每一个格子都按照"值"降序排序,然后按照这个降序的值遍历
            这样就可以保证步骤2中用到的比matrix[i][j]的值大的都是经过计算的有效dp值
        5.返回形式:返回dp[i][j]遍历过程中的最大值
        */
            // 阴间案例(也可以不用)
            if (matrix == null || matrix.length == 0) {
                return 0;
            }
            int m = matrix.length, n = matrix[0].length;
            // 保存最长递增路径
            int res = 1;
            // 状态定义
            int[][] dp = new int[m][n];
            // 初始化
            for (int i = 0; i < m; i++) {
                Arrays.fill(dp[i], 1);
            }
            // 用list集合存储按值降序格子的值和坐标
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    list.add(new int[]{matrix[i][j], i, j});
                }
            }
            Collections.sort(list, (a, b) -> b[0] - a[0]);
            // list.sort((a, b) -> b[0] - a[0]);
            // matrix[i][j]的4个方向
            int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            // 按照值降序遍历每个节点
            for (int[] ele : list) {
                int i = ele[1], j = ele[2], curVal = ele[0];
                // 遍历matrix[i][j]的每个方向
                for (int[] dir : dirs) {
                    // 计算出4个方向的坐标
                    int nextI = i + dir[0];
                    int nextJ = j + dir[1];
                    // 进行状态转移(注意符合条件的方向才能转移:格子在区域内并且该方向递增)
                    if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n &&
                            matrix[nextI][nextJ] > curVal) {
                        dp[i][j] = Math.max(dp[i][j], dp[nextI][nextJ] + 1);
                    }
                }
                // 维护每个节点的最长递增路径的最大值
                res = Math.max(res, dp[i][j]);
            }
            return res;
        }
    }
}
