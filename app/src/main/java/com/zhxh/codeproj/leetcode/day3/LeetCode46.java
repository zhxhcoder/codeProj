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
    public static void main(String[] args) {
        System.out.println(new Solution().permute(new int[]{1, 2, 3}));
    }

    /*
     回溯
     */
    static class Solution {
        public void backtrack(int n, ArrayList<Integer> nums, List<List<Integer>> output, int first) {
            // 如果所有整数都用完了
            if (first == n)
                //拷贝进去
                output.add(new ArrayList<Integer>(nums));
            for (int i = first; i < n; i++) {
                // 将第 i 个整数放在当前排列的首位
                Collections.swap(nums, first, i);
                // 使用下一个整数来完成排列
                backtrack(n, nums, output, first + 1);
                // 回溯
                Collections.swap(nums, first, i);
            }
        }

        public List<List<Integer>> permute(int[] nums) {
            // 初始化输出列表
            List<List<Integer>> output = new LinkedList();

            // 将 nums 转换为列表，因为输出是列表的列表
            ArrayList<Integer> nums_lst = new ArrayList<Integer>();
            for (int num : nums)
                nums_lst.add(num);

            int n = nums.length;
            backtrack(n, nums_lst, output, 0);
            return output;
        }
    }
}
