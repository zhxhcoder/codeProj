package com.zhxh.codeproj.leetcode.string;

import com.zhxh.codeproj.leetcode._base.Pair;

/*
比较两个版本号 version1和 version2。
如果version1>version2返回1，如果version1<version2 返回 -1， 除此之外返回 0。

你可以假设版本字符串非空，并且只包含数字和. 字符。

. 字符不代表小数点，而是用于分隔数字序列。

例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。

你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。


示例1:

输入: version1 = "0.1", version2 = "1.1"
输出: -1
示例 2:

输入: version1 = "1.0.1", version2 = "1"
输出: 1
示例 3:

输入: version1 = "7.5.2.4", version2 = "7.5.3"
输出: -1
示例4：

输入：version1 = "1.01", version2 = "1.001"
输出：0
解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。
示例 5：

输入：version1 = "1.0", version2 = "1.0.0"
输出：0
解释：version1 没有第三级修订号，这意味着它的第三级修订号默认为 “0”。


提示：

版本字符串由以点（.）分隔的数字字符串组成。这个数字字符串可能有前导零。
版本字符串不以点开始或结束，并且其中不会有两个连续的点。


 */
class LeetCode165 {
    public static void main(String[] args) {
        String version1 = "1.01", version2 = "1.001";
        System.out.println(new Solution().compareVersion(version1, version2));
        System.out.println(new Solution2().compareVersion(version1, version2));
    }

    //方法一：分割+解析，两次遍历，线性空间
    static class Solution {
        public int compareVersion(String version1, String version2) {
            String[] nums1 = version1.split("\\.");
            String[] nums2 = version2.split("\\.");
            int n1 = nums1.length, n2 = nums2.length;
            // compare versions
            int i1, i2;
            for (int i = 0; i < Math.max(n1, n2); ++i) {
                i1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
                i2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;
                if (i1 != i2) {
                    return i1 > i2 ? 1 : -1;
                }
            }
            // the versions are equal
            return 0;
        }
    }

    //方法二：双指针，一次遍历，常数空间
    static class Solution2 {
        public Pair<Integer, Integer> getNextChunk(String version, int n, int p) {
            // if pointer is set to the end of string
            // return 0
            if (p > n - 1) {
                return new Pair(0, p);
            }
            // find the end of chunk
            int i, pEnd = p;
            while (pEnd < n && version.charAt(pEnd) != '.') {
                ++pEnd;
            }
            // retrieve the chunk
            if (pEnd != n - 1) {
                i = Integer.parseInt(version.substring(p, pEnd));
            } else {
                i = Integer.parseInt(version.substring(p, n));
            }
            // find the beginning of next chunk
            p = pEnd + 1;
            return new Pair(i, p);
        }

        public int compareVersion(String version1, String version2) {
            int p1 = 0, p2 = 0;
            int n1 = version1.length(), n2 = version2.length();
            // compare versions
            int i1, i2;
            Pair<Integer, Integer> pair;
            while (p1 < n1 || p2 < n2) {
                pair = getNextChunk(version1, n1, p1);
                i1 = pair.first;
                p1 = pair.second;

                pair = getNextChunk(version2, n2, p2);
                i2 = pair.first;
                p2 = pair.second;
                if (i1 != i2) {
                    return i1 > i2 ? 1 : -1;
                }
            }
            // the versions are equal
            return 0;
        }
    }
}
