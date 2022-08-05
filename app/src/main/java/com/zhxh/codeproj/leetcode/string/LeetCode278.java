package com.zhxh.codeproj.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/*


 */
class LeetCode278 {
    public static void main(String[] args) {
        String s = "";
        System.out.println(new LeetCode06.Solution().convert(s, 4));
    }

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
            return false;
        }
    }

}
