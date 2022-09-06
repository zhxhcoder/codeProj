package com.zhxh.codeproj.leetcode.ace100.backtrack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Arrays;

/*
给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

示例 1：

输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
示例 2：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

提示：
1 <= nums.length <= 8
-10 <= nums[i] <= 10

 */
public class LeetCode47 {
    //todo 回溯-backtrack 参考17、46、131
    public static void main(String[] args) {
        System.out.println(new Solution().permuteUnique(new int[]{1, 1, 2, 3}));
        System.out.println(new Solution2().permuteUnique(new int[]{1, 1, 2, 3}));
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
    static class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len == 0) {
                return res;
            }
            // 排序（升序或者降序都可以），排序是剪枝的前提
            Arrays.sort(nums);
            boolean[] used = new boolean[len];
            // 使用 Deque 是 Java 官方 Stack 类的建议
            Deque<Integer> path = new ArrayDeque<>(len);
            dfs(nums, 0, used, path, res);
            return res;
        }

        private void dfs(int[] nums, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
            if (depth == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; ++i) {
                if (used[i]) {
                    continue;
                }
                // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
                // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }

                path.addLast(nums[i]);
                used[i] = true;

                dfs(nums, depth + 1, used, path, res);
                // 回溯部分的代码，和 dfs 之前的代码是对称的
                used[i] = false;
                path.removeLast();
            }
        }
    }

    /*
    搜索回溯
     */
    static class Solution2 {
        boolean[] used;

        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            used = new boolean[nums.length];
            Arrays.sort(nums);
            backtrack(nums, res, 0, path);
            return res;
        }

        public void backtrack(int[] nums, List<List<Integer>> res, int depth, List<Integer> path) {
            if (depth == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; ++i) {
                if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                    continue;
                }
                path.add(nums[i]);
                used[i] = true;
                backtrack(nums, res, depth + 1, path);
                used[i] = false;
                path.remove(depth);
            }
        }
    }
}
