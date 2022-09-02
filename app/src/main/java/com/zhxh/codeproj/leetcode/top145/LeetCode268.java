package com.zhxh.codeproj.leetcode.top145;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。



示例 1：

输入：nums = [3,0,1]
输出：2
解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
示例 2：

输入：nums = [0,1]
输出：2
解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
示例 3：

输入：nums = [9,6,4,2,3,5,7,0,1]
输出：8
解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
示例 4：

输入：nums = [0]
输出：1
解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。


提示：

n == nums.length
1 <= n <= 104
0 <= nums[i] <= n
nums 中的所有数字都 独一无二

 */
public class LeetCode268 {
    public static void main(String[] args) {
        System.out.println(new Solution().missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(new Solution2().missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(new Solution3().missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(new Solution4().missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    /*
    方法一：排序
     */
    static class Solution {
        public int missingNumber(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] != i) {
                    return i;
                }
            }
            return n;
        }
    }

    /*
    方法二：哈希集合
     */
    static class Solution2 {
        public int missingNumber(int[] nums) {
            Set<Integer> set = new HashSet<Integer>();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                set.add(nums[i]);
            }
            int missing = -1;
            for (int i = 0; i <= n; i++) {
                if (!set.contains(i)) {
                    missing = i;
                    break;
                }
            }
            return missing;
        }
    }

    /*
    方法三：位运算
     */
    static class Solution3 {
        public int missingNumber(int[] nums) {
            int xor = 0;
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                xor ^= nums[i];
            }
            for (int i = 0; i <= n; i++) {
                xor ^= i;
            }
            return xor;
        }
    }

    /*
    方法四：数学
     */
    static class Solution4 {
        public int missingNumber(int[] nums) {
            int n = nums.length;
            int total = n * (n + 1) / 2;
            int arrSum = 0;
            for (int i = 0; i < n; i++) {
                arrSum += nums[i];
            }
            return total - arrSum;
        }
    }
}
