package com.zhxh.codeproj.leetcode.hot100;

/*
在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。



示例 1：


输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
输出：4
示例 2：


输入：matrix = [["0","1"],["1","0"]]
输出：1
示例 3：

输入：matrix = [["0"]]
输出：0


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] 为 '0' 或 '1'

 */
public class LeetCode221 {
    public static void main(String[] args) {
        System.out.println(new Solution().maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
        System.out.println(new Solution2().maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
    }

    /*
    暴力法
     */
    static class Solution {
        public int maximalSquare(char[][] matrix) {
            int maxSide = 0;
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return maxSide;
            }
            int rows = matrix.length, columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (matrix[i][j] == '1') {
                        // 遇到一个 1 作为正方形的左上角
                        maxSide = Math.max(maxSide, 1);
                        // 计算可能的最大正方形边长
                        int currentMaxSide = Math.min(rows - i, columns - j);
                        for (int k = 1; k < currentMaxSide; k++) {
                            // 判断新增的一行一列是否均为 1
                            boolean flag = true;
                            if (matrix[i + k][j + k] == '0') {
                                break;
                            }
                            for (int m = 0; m < k; m++) {
                                if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                maxSide = Math.max(maxSide, k + 1);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
            int maxSquare = maxSide * maxSide;
            return maxSquare;
        }
    }

    /*
    动态规划
     */
    static class Solution2 {
        public int maximalSquare(char[][] matrix) {
            int maxSide = 0;
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return maxSide;
            }
            int rows = matrix.length, columns = matrix[0].length;
            int[][] dp = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (matrix[i][j] == '1') {
                        if (i == 0 || j == 0) {
                            dp[i][j] = 1;
                        } else {
                            dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                        }
                        maxSide = Math.max(maxSide, dp[i][j]);
                    }
                }
            }
            int maxSquare = maxSide * maxSide;
            return maxSquare;
        }
    }
}
