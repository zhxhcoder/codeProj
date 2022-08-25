package com.zhxh.codeproj.leetcode.day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
    }

    /*
     回溯

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
}
