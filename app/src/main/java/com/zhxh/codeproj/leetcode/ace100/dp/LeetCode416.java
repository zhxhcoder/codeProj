package com.zhxh.codeproj.leetcode.ace100.dp;

import java.util.Arrays;

/*
416. 分割等和子集
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

示例 1：

输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。
示例 2：

输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。

提示：

1 <= nums.length <= 200
1 <= nums[i] <= 100
 */
class LeetCode416 {
    public static void main(String[] args) {
        System.out.println(new Solution().canPartition(new int[]{1, 3, 5, 11, 5}));
        System.out.println(new Solution2().canPartition(new int[]{1, 3, 5, 11, 5}));
        System.out.println(new Solution3().canPartition(new int[]{1, 3, 5, 11, 5}));
    }

    /*
       关于 0-1 背包问题的介绍，大家可以在互联网上搜索《背包九讲》进行相关知识的学习。本题解有些地方使用了 0-1 背包问题的描述，因此会不加解释的使用“背包”、“容量”这样的名词。
       本题解按照动态规划的一般思考方向进行讲解（仅供参考，本人水平有限，大概觉得是这几个方面），它们是：

       1、状态定义；
       2、状态转移方程；
       3、初始化；
       4、输出；
       5、思考状态压缩。

       这 5 个部分是本题解的结构。其它类似的动态规划问题也可以按照这样的方向去思考、解释和理解。

       事实上，这是一个典型的“动态规划”问题，并且它的“原形”是“0-1 背包问题”。使用“动态规划”解决问题的思路是“以空间换时间”，“规划”这个词在英文中就是“填表格”的意思，代码执行的过程，也可以称之为“填表格”。
       “动态规划”的方法可以认为是为我们提供了一个思考问题的方向，我们不是直接面对问题求解，而是去找原始问题（或者说和原始问题相关的问题）的最开始的样子，通过“状态转移方程”（这里没法再解释了，可以结合下文理解）记录下每一步求解的结果，直到最终问题解决。
       而直接面对问题求解，就是我们熟悉的“递归”方法，由于有大量重复子问题，我们就需要加缓存，这叫“记忆化递归”，这里就不给参考代码了，感兴趣的朋友可以自己写一下，比较一下它们两种思考方式的不同之处和优缺点。
       做这道题需要做这样一个等价转换：是否可以从这个数组中挑选出一些正整数，使得这些数的和等于整个数组元素的和的一半。前提条件是：数组的和一定得是偶数，即数组的和一定得被 22 整除，这一点是特判。
     */
    static class Solution {
        public boolean canPartition(int[] nums) {
            int len = nums.length;
            if (len < 2) {
                return false;
            }

            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            // 特判：如果是奇数，就不符合要求
            if ((sum & 1) == 1) {
                return false;
            }

            int target = sum / 2;

            // 创建二维状态数组，行：物品索引，列：容量（包括 0）
            boolean[][] dp = new boolean[len][target + 1];

            // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
            if (nums[0] <= target) {
                dp[0][nums[0]] = true;
            }

            // 再填表格后面几行
            for (int i = 1; i < len; i++) {
                for (int j = 0; j <= target; j++) {
                    // 直接从上一行先把结果抄下来，然后再修正
                    dp[i][j] = dp[i - 1][j];

                    if (nums[i] == j) {
                        dp[i][j] = true;
                        continue;
                    }
                    if (nums[i] < j) {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                    }
                }
            }
            return dp[len - 1][target];
        }
    }

    /*
    背包问题
     */
    static class Solution2 {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            if (sum % 2 == 1) {
                return false;
            }

            //背包容量
            int target = sum / 2;
            //物品个数
            int len = nums.length;

            //状态：dp[m][n]表示背包容量为 m，有 前 n 个物品（ [0, n-1]共n个 ）时能否装满背包
            boolean[][] dp = new boolean[target + 1][len + 1];

            // base case
            // dp[0][0] = true, 不选即满
            // dp[0][i] = true, i >= 1; 表示容量为0，有1个及以上个物品时可认为不选即满，固可以填满
            // dp[i][0] = false, i >= 1; 表示容量 大于等于 1时，没有物品可选则无法填满
            for (int i = 0; i < len + 1; i++) {
                dp[0][i] = true;
            }

            for (int i = 1; i < target + 1; i++) {
                for (int j = 1; j < len + 1; j++) {
                    // 如果不能装下前j个物品中的最后一个
                    if (i < nums[j - 1]) {
                        dp[i][j] = dp[i][j - 1];
                        // 能装下，可以选择装或者不装
                    } else if (i >= nums[j - 1]) {
                        dp[i][j] = dp[i][j - 1] || dp[i - nums[j - 1]][j - 1];
                    }
                }
            }
            return dp[target][len];
        }
    }

    /*
    官方解答
     */
    static class Solution3 {
        public boolean canPartition(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return false;
            }
            int sum = 0, maxNum = 0;
            for (int num : nums) {
                sum += num;
                maxNum = Math.max(maxNum, num);
            }
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            if (maxNum > target) {
                return false;
            }
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;
            for (int i = 0; i < n; i++) {
                int num = nums[i];
                for (int j = target; j >= num; --j) {
                    dp[j] |= dp[j - num];
                }
            }
            return dp[target];
        }
    }
}
