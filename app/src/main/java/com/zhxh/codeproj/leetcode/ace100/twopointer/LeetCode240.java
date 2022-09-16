package com.zhxh.codeproj.leetcode.ace100.twopointer;

/*
240. 搜索二维矩阵 II
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。


示例 1：


输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
输出：true
示例 2：


输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
输出：false


提示：

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matrix[i][j] <= 109
每行的所有元素从左到右升序排列
每列的所有元素从上到下升序排列
-109 <= target <= 109
 */
public class LeetCode240 {
    public static void main(String[] args) {
        System.out.println(new Solution().searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5));
        System.out.println(new Solution2().searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5));
        System.out.println(new Solution3().searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5));
        System.out.println(new Solution4().searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 5));
    }

    /*
    暴力搜索
     */
    static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    /*
    二分查找
     */
    static class Solution2 {
        public boolean searchMatrix(int[][] matrix, int target) {
            for (int[] row : matrix) {
                int index = search(row, target);
                if (index >= 0) {
                    return true;
                }
            }
            return false;
        }

        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                int num = nums[mid];
                if (num == target) {
                    return mid;
                } else if (num > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return -1;
        }
    }


    static class Solution3 {
        private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
            int lo = start;
            int hi = vertical ? matrix[0].length - 1 : matrix.length - 1;
            while (hi >= lo) {
                int mid = (lo + hi) / 2;
                if (vertical) { //搜索列
                    if (matrix[start][mid] < target) {
                        lo = mid + 1;
                    } else if (matrix[start][mid] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                } else { //搜索行
                    if (matrix[mid][start] < target) {
                        lo = mid + 1;
                    } else if (matrix[mid][start] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean searchMatrix(int[][] matrix, int target) {
            // 一个空矩阵显然不包含“目标”
            if (matrix == null || matrix.length == 0) {
                return false;
            }
            // 遍历矩阵对角线
            int shorterDim = Math.min(matrix.length, matrix[0].length);
            for (int i = 0; i < shorterDim; i++) {
                boolean verticalFound = binarySearch(matrix, target, i, true);
                boolean horizontalFound = binarySearch(matrix, target, i, false);
                if (verticalFound || horizontalFound) {
                    return true;
                }
            }
            return false;
        }
    }

    /*
    Z 字形查找
     */
    static class Solution4 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int x = 0, y = n - 1;
            while (x < m && y >= 0) {
                if (matrix[x][y] == target) {
                    return true;
                }
                if (matrix[x][y] > target) {
                    --y;
                } else {
                    ++x;
                }
            }
            return false;
        }
    }
}
