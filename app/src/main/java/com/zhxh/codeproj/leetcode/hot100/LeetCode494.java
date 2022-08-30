package com.zhxh.codeproj.leetcode.hot100;

/*
给你一个整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。



示例 1：

输入：nums = [1,1,1,1,1], target = 3
输出：5
解释：一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
示例 2：

输入：nums = [1], target = 1
输出：1


提示：

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000

 */
public class LeetCode494 {
    public static void main(String[] args) {
        System.out.println(new Solution().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(new Solution2().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(new Solution3().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }

    /*
    回溯
     */
    static class Solution {
        int count = 0;

        public int findTargetSumWays(int[] nums, int target) {
            backtrack(nums, target, 0, 0);
            return count;
        }

        public void backtrack(int[] nums, int target, int index, int sum) {
            if (index == nums.length) {
                if (sum == target) {
                    count++;
                }
            } else {
                backtrack(nums, target, index + 1, sum + nums[index]);
                backtrack(nums, target, index + 1, sum - nums[index]);
            }
        }
    }

    /*
    动态规划
     */
    static class Solution2 {
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            int diff = sum - target;
            if (diff < 0 || diff % 2 != 0) {
                return 0;
            }
            int n = nums.length, neg = diff / 2;
            int[][] dp = new int[n + 1][neg + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                int num = nums[i - 1];
                for (int j = 0; j <= neg; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= num) {
                        dp[i][j] += dp[i - 1][j - num];
                    }
                }
            }
            return dp[n][neg];
        }
    }

    /*
    动态规划-优化
     */
    static class Solution3 {
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            int diff = sum - target;
            if (diff < 0 || diff % 2 != 0) {
                return 0;
            }
            int neg = diff / 2;
            int[] dp = new int[neg + 1];
            dp[0] = 1;
            for (int num : nums) {
                for (int j = neg; j >= num; j--) {
                    dp[j] += dp[j - num];
                }
            }
            return dp[neg];
        }
    }
}
