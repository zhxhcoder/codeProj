package com.zhxh.codeproj.leetcode;

public class LeetCode48 {
    public static void main(String[] args) {

        Solution solution = new Solution();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        for (int[] list : matrix) {
            System.out.println("---------");

            for (Integer item : list) {
                System.out.println(item);
            }
        }

        solution.rotate(matrix);

        for (int[] list : matrix) {
            System.out.println("********");

            for (Integer item : list) {
                System.out.println(item);
            }
        }
    }

    static class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;

            // transpose matrix
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    int tmp = matrix[j][i];
                    matrix[j][i] = matrix[i][j];
                    matrix[i][j] = tmp;
                }
            }
            // reverse each row
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n / 2; j++) {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[i][n - j - 1];
                    matrix[i][n - j - 1] = tmp;
                }
            }
        }
    }
}
