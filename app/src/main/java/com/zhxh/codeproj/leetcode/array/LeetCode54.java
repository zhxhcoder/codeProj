package com.zhxh.codeproj.leetcode.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
给定一个包含m x n个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

示例1:

输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]
示例2:

输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]

 */
class LeetCode54 {
    public static void main(String[] args) {
        System.out.println(new Solution().spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}}));
        System.out.println(new Solution2().spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}}));
        System.out.println(new Solution3().spiralOrder(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}}));
    }

    /*
    模拟
     */
    static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> order = new ArrayList<>();
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return order;
            }
            int rows = matrix.length, columns = matrix[0].length;
            boolean[][] visited = new boolean[rows][columns];
            int total = rows * columns;
            int row = 0, column = 0;
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int directionIndex = 0;
            for (int i = 0; i < total; i++) {
                order.add(matrix[row][column]);
                visited[row][column] = true;
                int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
                if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                    directionIndex = (directionIndex + 1) % 4;
                }
                row += directions[directionIndex][0];
                column += directions[directionIndex][1];
            }
            return order;
        }
    }

    /*
    按层遍历
     */
    static class Solution2 {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> order = new ArrayList<Integer>();
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return order;
            }
            int rows = matrix.length, columns = matrix[0].length;
            int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
            while (left <= right && top <= bottom) {
                for (int column = left; column <= right; column++) {
                    order.add(matrix[top][column]);
                }
                for (int row = top + 1; row <= bottom; row++) {
                    order.add(matrix[row][right]);
                }
                if (left < right && top < bottom) {
                    for (int column = right - 1; column > left; column--) {
                        order.add(matrix[bottom][column]);
                    }
                    for (int row = bottom; row > top; row--) {
                        order.add(matrix[row][left]);
                    }
                }
                left++;
                right--;
                top++;
                bottom--;
            }
            return order;
        }
    }

    /*
    顺时针顺序
    n++,m++,n--,m-- 这样循环的来
     */
    static class Solution3 {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> ans = new ArrayList<>();

            //总元素
            int num = matrix.length * matrix[0].length;

            int i = 0, j = 0;
            int count = 0;

            //0,1,2,4 右，下，左，上
            int arrow = 0;
            int mBottom = matrix.length;
            int mTop = -1;
            int nRight = matrix[0].length;
            int nLeft = -1;
            while (count < num) {
                if (i < matrix.length && j < matrix[0].length) {
                    ans.add(matrix[i][j]);
                    count++;
                }

                if (arrow == 0) {//右
                    j++;
                } else if (arrow == 1) {//下
                    i++;
                } else if (arrow == 2) {//左
                    j--;
                } else if (arrow == 3) {//上
                    i--;
                }

                if (j == nRight) {//撞到右墙
                    arrow = 1;
                    nRight--;
                }

                if (i == mBottom) {//撞到下墙
                    arrow = 2;
                    mBottom--;
                }

                if (i == nLeft) {//撞到左墙
                    arrow = 3;
                    mBottom++;
                }

                if (j == mTop) {//撞到上墙
                    arrow = 0;
                    nLeft++;
                }
            }

            return ans;
        }
    }
}
