package com.zhxh.codeproj.leetcode.ace100.bsearch;

/*
你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。

你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。


示例 1：

输入：n = 5, bad = 4
输出：4
解释：
调用 isBadVersion(3) -> false 
调用 isBadVersion(5)-> true 
调用 isBadVersion(4)-> true
所以，4 是第一个错误的版本。
示例 2：

输入：n = 1, bad = 1
输出：1


提示：

1 <= bad <= n <= 231 - 1


 */
class LeetCode278 {
    public static void main(String[] args) {
        System.out.println(new Solution().firstBadVersion(5));
    }

    /*
    二分法小套路
1.while 永远是 while(left <= right)
2.将middle会落的地方分成两份
3.内部 永远是 left = middle + 1 和 right = middle - 1
如果答案落在left = middle + 1 的里面， 最终return right
如果答案落在right= middle - 1 里面， 最终return left

     */
    static class Solution {
        public int firstBadVersion(int n) {
            int left = 1, right = n;
            while (left <= right) {
                int middle = left + (right - left) / 2;
                if (isBadVersion(middle)) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            return left;
        }

        private boolean isBadVersion(int middle) {
            return middle == 3;
        }
    }
}
