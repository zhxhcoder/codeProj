package com.zhxh.codeproj.leetcode.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为target 的不同组合数少于 150 个。


示例1：

输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。
示例2：

输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]
示例 3：

输入: candidates = [2], target = 1
输出: []

提示：

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
candidate 中的每个元素都 互不相同
1 <= target <= 500

 */
public class LeetCode39 {
    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum(new int[]{2, 3, 5}, 8));
        System.out.println(new Solution2().combinationSum(new int[]{2, 3, 5}, 8));
        System.out.println(new Solution3().combinationSum(new int[]{2, 3, 5}, 8));
    }

    static class Solution {
        private List<List<Integer>> res = new ArrayList<>();
        private int[] candidates;
        private int len;

        private void findCombinationSum(int residue, int start, Stack<Integer> pre) {
            if (residue == 0) {
                // Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
                res.add(new ArrayList<>(pre));
                return;
            }
            // 优化添加的代码2：在循环的时候做判断，尽量避免系统栈的深度
            // residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行后面的循环了
            // 这一点基于原始数组是排序数组的前提，因为如果计算后面的剩余，只会越来越小
            for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
                pre.add(candidates[i]);
                // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
                findCombinationSum(residue - candidates[i], i, pre);
                pre.pop();
            }
        }

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            int len = candidates.length;
            if (len == 0) {
                return res;
            }
            // 优化添加的代码1：先对数组排序，可以提前终止判断
            Arrays.sort(candidates);
            this.len = len;
            this.candidates = candidates;
            findCombinationSum(target, 0, new Stack<>());
            return res;
        }
    }

    /*

    搜索回溯
    对于这种寻找所以可行解的题，我们都可以尝试用"搜索回溯"的方法来解决
    更形象化地说，如果我们将整个搜索过程用一个树来表达，每次的搜索都会延伸出两个分叉，直到递归的终止条件，这样我们就能不重复且不遗漏地找到所有可行解；

     */
    static class Solution2 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            List<Integer> combine = new ArrayList<Integer>();
            dfs(candidates, target, ans, combine, 0);
            return ans;
        }

        public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
            if (idx == candidates.length) {
                return;
            }
            if (target == 0) {
                ans.add(new ArrayList<Integer>(combine));
                return;
            }
            // 直接跳过
            dfs(candidates, target, ans, combine, idx + 1);
            // 选择当前数
            if (target - candidates[idx] >= 0) {
                combine.add(candidates[idx]);
                dfs(candidates, target - candidates[idx], ans, combine, idx);
                combine.remove(combine.size() - 1);
            }
        }
    }

    static class Solution3 {

        private List<List<Integer>> res = new ArrayList<>();
        private int[] candidates;
        private int len;

        private void findCombinationSum(int residue, int start, Stack<Integer> pre) {
            if (residue == 0) {
                // Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
                res.add(new ArrayList<>(pre));
                return;
            }
            // 优化添加的代码2：在循环的时候做判断，尽量避免系统栈的深度
            // residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行后面的循环了
            // 这一点基于原始数组是排序数组的前提，因为如果计算后面的剩余，只会越来越小
            for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
                pre.add(candidates[i]);
                // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
                findCombinationSum(residue - candidates[i], i, pre);
                pre.pop();
            }
        }

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            int len = candidates.length;
            if (len == 0) {
                return res;
            }
            // 优化添加的代码1：先对数组排序，可以提前终止判断
            Arrays.sort(candidates);
            this.len = len;
            this.candidates = candidates;
            findCombinationSum(target, 0, new Stack<>());
            return res;
        }
    }

}
