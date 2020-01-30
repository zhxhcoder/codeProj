package com.zhxh.codeproj.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LeetCode448 {

    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 2, 6, 7};
        Solution solution = new Solution();
        List<Integer> results = solution.findDisappearedNumbers(nums);

        for (Integer i : results) {
            System.out.println(i);
        }
    }

    static class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            HashMap<Integer, Boolean> hashTable = new HashMap<Integer, Boolean>();

            for (int i = 0; i < nums.length; i++) {
                hashTable.put(nums[i], true);
            }

            List<Integer> result = new LinkedList<Integer>();

            for (int i = 1; i <= nums.length; i++) {
                if (!hashTable.containsKey(i)) {
                    result.add(i);
                }
            }

            return result;
        }
    }
}
