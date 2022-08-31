package com.zhxh.codeproj.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
给定一个由 0 和 1 组成的矩阵 mat，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
两个相邻元素间的距离为 1 。

示例 1：


输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
输出：[[0,0,0],[0,1,0],[0,0,0]]
示例 2：

输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
输出：[[0,0,0],[0,1,0],[1,2,1]]


提示：

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
mat 中至少有一个 0

 */
public class LeetCode542 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}})));
        System.out.println(Arrays.deepToString(new Solution2().updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}})));
        System.out.println(Arrays.deepToString(new Solution3().updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}})));
    }

    /*
    广度优先
     */
    static class Solution {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int[][] updateMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[][] dist = new int[m][n];
            boolean[][] seen = new boolean[m][n];
            Queue<int[]> queue = new LinkedList<int[]>();
            // 将所有的 0 添加进初始队列中
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == 0) {
                        queue.offer(new int[]{i, j});
                        seen[i][j] = true;
                    }
                }
            }
            // 广度优先搜索
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int i = cell[0], j = cell[1];
                for (int d = 0; d < 4; ++d) {
                    int ni = i + dirs[d][0];
                    int nj = j + dirs[d][1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && !seen[ni][nj]) {
                        dist[ni][nj] = dist[i][j] + 1;
                        queue.offer(new int[]{ni, nj});
                        seen[ni][nj] = true;
                    }
                }
            }
            return dist;
        }
    }

    /*
    动态规划
     */
    static class Solution2 {
        public int[][] updateMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            // 初始化动态规划的数组，所有的距离值都设置为一个很大的数
            int[][] dist = new int[m][n];
            for (int i = 0; i < m; ++i) {
                Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
            }
            // 如果 (i, j) 的元素为 0，那么距离为 0
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == 0) {
                        dist[i][j] = 0;
                    }
                }
            }
            // 只有 水平向左移动 和 竖直向上移动，注意动态规划的计算顺序
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i - 1 >= 0) {
                        dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                    }
                    if (j - 1 >= 0) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                    }
                }
            }
            // 只有 水平向左移动 和 竖直向下移动，注意动态规划的计算顺序
            for (int i = m - 1; i >= 0; --i) {
                for (int j = 0; j < n; ++j) {
                    if (i + 1 < m) {
                        dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                    }
                    if (j - 1 >= 0) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                    }
                }
            }
            // 只有 水平向右移动 和 竖直向上移动，注意动态规划的计算顺序
            for (int i = 0; i < m; ++i) {
                for (int j = n - 1; j >= 0; --j) {
                    if (i - 1 >= 0) {
                        dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                    }
                    if (j + 1 < n) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                    }
                }
            }
            // 只有 水平向右移动 和 竖直向下移动，注意动态规划的计算顺序
            for (int i = m - 1; i >= 0; --i) {
                for (int j = n - 1; j >= 0; --j) {
                    if (i + 1 < m) {
                        dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                    }
                    if (j + 1 < n) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                    }
                }
            }
            return dist;
        }
    }

    static class Solution3 {
        public int[][] updateMatrix(int[][] matrix) {
            Queue<int[]> queue = new LinkedList<>();
            int[] directions = {-1, 0, 1, 0, -1};

            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    if (matrix[row][col] == 0) {
                        queue.offer(new int[]{row, col});
                    } else {
                        //标记非零元素为负，和遍历后设定的正数距离加以区分
                        matrix[row][col] = -1;
                    }
                }
            }

            int step = 1;
            while (!queue.isEmpty()) {
                //对当前队列中所有零元素遍历，所有元素向四周走一步
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    //获取队列中的元素位置
                    int[] cur = queue.poll();
                    //向四个方向依次走一步
                    for (int j = 0; j < directions.length - 1; j++) {
                        int x = cur[0] + directions[j];
                        int y = cur[1] + directions[j + 1];
                        //如果超出矩阵范围，或者遇见零元素及设置过距离step的元素则跳过，只对未遍历到的-1操作
                        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] >= 0) {
                            continue;
                        }
                        matrix[x][y] = step;
                        queue.offer(new int[]{x, y});
                    }
                }
                //下次遍历到的-1元素相比前一次距离step加1
                step++;
            }
            return matrix;
        }
    }
}
