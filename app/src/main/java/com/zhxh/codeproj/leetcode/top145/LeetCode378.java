package com.zhxh.codeproj.leetcode.top145;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。

你必须找到一个内存复杂度优于 O(n2) 的解决方案。

 

示例 1：

输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
输出：13
解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
示例 2：

输入：matrix = [[-5]], k = 1
输出：-5
 

提示：

n == matrix.length
n == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 109
题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
1 <= k <= n2
 

进阶：

你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章（ this paper ）很有趣。

 */
public class LeetCode378 {
    public static void main(String[] args) {
        System.out.println(new Solution().kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
        System.out.println(new Solution2().kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
    }

    /*
    方法一：直接排序
     */
    static class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            int rows = matrix.length, columns = matrix[0].length;
            int[] sorted = new int[rows * columns];
            int index = 0;
            for (int[] row : matrix) {
                for (int num : row) {
                    sorted[index++] = num;
                }
            }
            Arrays.sort(sorted);
            return sorted[k - 1];
        }
    }


    /*
    方法二：归并排序
    由题目给出的性质可知，这个矩阵的每一行均为一个有序数组。
    问题即转化为从这 n 个有序数组中找第 k 大的数，可以想到利用归并排序的做法，归并到第 k 个数即可停止。

     */
    static class Solution2 {
        public int kthSmallest(int[][] matrix, int k) {
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            });
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                pq.offer(new int[]{matrix[i][0], i, 0});
            }
            for (int i = 0; i < k - 1; i++) {
                int[] now = pq.poll();
                if (now[2] != n - 1) {
                    pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
                }
            }
            return pq.poll()[0];
        }
    }

    /*
    方法三：二分查找
     */
    static class Solution3 {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            int left = matrix[0][0];
            int right = matrix[n - 1][n - 1];
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (check(matrix, mid, k, n)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        public boolean check(int[][] matrix, int mid, int k, int n) {
            int i = n - 1;
            int j = 0;
            int num = 0;
            while (i >= 0 && j < n) {
                if (matrix[i][j] <= mid) {
                    num += i + 1;
                    j++;
                } else {
                    i--;
                }
            }
            return num >= k;
        }
    }
}
