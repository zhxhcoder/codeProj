package com.zhxh.codeproj.leetcode.day3;

import java.util.*;

/*

给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */
public class LeetCode46 {
    //todo 回溯-backtrack
    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[]{1, 2, 3}));
        System.out.println(new Solution2().permute(new int[]{1, 2, 3}));
    }

    /*
     回溯
     深度优先遍历+状态重置
     */
    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            // 初始化输出列表
            List<List<Integer>> res = new LinkedList<>();

            // 将 nums 转换为列表，因为输出是列表的列表
            ArrayList<Integer> output = new ArrayList<Integer>();
            for (int num : nums)
                output.add(num);

            int n = nums.length;
            backtrack(n, output, res, 0);
            return res;
        }

        public void backtrack(int n, ArrayList<Integer> output, List<List<Integer>> res, int first) {
            // 如果所有整数都用完了
            if (first == n) {
                //拷贝进去
                res.add(new ArrayList<Integer>(output));
            }

            for (int i = first; i < n; i++) {
                // 维护动态数组  将第 i 个整数放在当前排列的首位
                Collections.swap(output, first, i);
                // 继续递归填下一个数 使用下一个整数来完成排列
                backtrack(n, output, res, first + 1);
                // 回溯
                Collections.swap(output, first, i);
            }
        }
    }

    /*
     回溯
     深度优先遍历+状态重置
     状态：每个结点表示求解问题的不同阶段
     状态变量：
     1，递归到第几层 depth
     2，已经选了哪些数 path
     3，布尔数组 used
     */
    static class Solution2 {
        public List<List<Integer>> permute(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0) return res;
            //栈
            Deque<Integer> path = new ArrayDeque<>();
            boolean[] used = new boolean[len];
            dfs(nums, len, 0, path, used, res);
            return res;
        }

        private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
            if (depth == len) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < len; i++) {
                if (used[i]) {
                    continue;
                }
                //栈
                path.addLast(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, path, used, res);
                //回溯
                path.removeLast();
                used[i] = false;
            }
        }
    }
}
