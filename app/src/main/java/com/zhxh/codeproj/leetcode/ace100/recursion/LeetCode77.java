package com.zhxh.codeproj.leetcode.ace100.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
77. 组合
给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。

你可以按 任何顺序 返回答案。



示例 1：

输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
示例 2：

输入：n = 1, k = 1
输出：[[1]]


提示：

1 <= n <= 20
1 <= k <= n
 */
public class LeetCode77 {
    public static void main(String[] args) {
        //todo 回溯-backtrack
        System.out.println(new Solution().combine(4, 2));
        System.out.println(new Solution2().combine(4, 2));
        System.out.println(new Solution3().combine(4, 2));
    }

    static class Solution {
        List<List<Integer>> output = new LinkedList<>();
        int n;
        int k;

        public void backtrack(int first, LinkedList<Integer> curr) {
            // 如果组合完成
            if (curr.size() == k){
                output.add(new LinkedList<>(curr));
            }

            for (int i = first; i <= n; ++i) {
                // 将 i 添加到当前组合中
                curr.add(i);
                // 使用下一个整数来完成组合
                backtrack(i + 1, curr);
                // 回溯
                curr.removeLast();
            }
        }

        public List<List<Integer>> combine(int n, int k) {
            this.n = n;
            this.k = k;
            backtrack(1, new LinkedList<Integer>());
            return output;
        }
    }

    /*
    方法一：递归实现组合型枚举
     */
    static class Solution2 {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        public List<List<Integer>> combine(int n, int k) {
            dfs(1, n, k);
            return ans;
        }

        public void dfs(int cur, int n, int k) {
            // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
            if (temp.size() + (n - cur + 1) < k) {
                return;
            }
            // 记录合法的答案
            if (temp.size() == k) {
                ans.add(new ArrayList<Integer>(temp));
                return;
            }
            // 考虑选择当前位置
            temp.add(cur);
            dfs(cur + 1, n, k);
            temp.remove(temp.size() - 1);
            // 考虑不选择当前位置
            dfs(cur + 1, n, k);
        }
    }

    /*
    方法二：非递归（字典序法）实现组合型枚举
     */
    static class Solution3 {
        public List<List<Integer>> combine(int n, int k) {
            List<Integer> temp = new ArrayList<Integer>();
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            // 初始化
            // 将 temp 中 [0, k - 1] 每个位置 i 设置为 i + 1，即 [0, k - 1] 存 [1, k]
            // 末尾加一位 n + 1 作为哨兵
            for (int i = 1; i <= k; ++i) {
                temp.add(i);
            }
            temp.add(n + 1);

            int j = 0;
            while (j < k) {
                ans.add(new ArrayList<Integer>(temp.subList(0, k)));
                j = 0;
                // 寻找第一个 temp[j] + 1 != temp[j + 1] 的位置 t
                // 我们需要把 [0, t - 1] 区间内的每个位置重置成 [1, t]
                while (j < k && temp.get(j) + 1 == temp.get(j + 1)) {
                    temp.set(j, j + 1);
                    ++j;
                }
                // j 是第一个 temp[j] + 1 != temp[j + 1] 的位置
                temp.set(j, temp.get(j) + 1);
            }
            return ans;
        }
    }
}
