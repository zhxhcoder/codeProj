package com.zhxh.codeproj.leetcode.top145;

import java.util.Arrays;
import java.util.Comparator;

/*
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

 

示例 1：

输入：nums = [10,2]
输出："210"
示例 2：

输入：nums = [3,30,34,5,9]
输出："9534330"
 

提示：

1 <= nums.length <= 100
0 <= nums[i] <= 109

 */
public class LeetCode179 {
    public static void main(String[] args) {
        System.out.println(new Solution().largestNumber(new int[]{3, 30, 34, 5, 9}));
        System.out.println(new Solution2().largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    /*
    方法一：排序
     */
    static class Solution {
        public String largestNumber(int[] nums) {
            int n = nums.length;
            // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
            Integer[] numsArr = new Integer[n];
            for (int i = 0; i < n; i++) {
                numsArr[i] = nums[i];
            }

            Arrays.sort(numsArr, (x, y) -> {
                long sx = 10, sy = 10;
                while (sx <= x) {
                    sx *= 10;
                }
                while (sy <= y) {
                    sy *= 10;
                }
                return (int) (-sy * x - y + sx * y + x);
            });

            if (numsArr[0] == 0) {
                return "0";
            }
            StringBuilder ret = new StringBuilder();
            for (int num : numsArr) {
                ret.append(num);
            }
            return ret.toString();
        }
    }

    static class Solution2 {
        public String largestNumber(int[] nums) {
            // 获取输入整数作为字符串。
            String[] asStrs = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                asStrs[i] = String.valueOf(nums[i]);
            }
            // 根据自定义比较器对字符串进行排序。
            Arrays.sort(asStrs, new Comparator<String>() {
                @Override
                public int compare(String a, String b) {
                    String order1 = a + b;
                    String order2 = b + a;
                    return order2.compareTo(order1);
                }
            });

            // 如果排序后最大的数为0，则整个数为零。
            if (asStrs[0].equals("0")) {
                return "0";
            }
            // 从排序数组中构建最大数。
            StringBuilder largestNumberStr = new StringBuilder("");
            for (String numAsStr : asStrs) {
                largestNumberStr.append(numAsStr);
            }
            return largestNumberStr.toString();
        }
    }
}
