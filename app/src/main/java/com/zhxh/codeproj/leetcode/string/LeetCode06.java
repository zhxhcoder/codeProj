package com.zhxh.codeproj.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/*
z 字形变换
将一个给定字符串根据给定的行数，以从上往下、从左到右进行Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING"行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例2:

输入: s = "LEETCODEISHIRING", numRows =4
输出:"LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G

 */
class LeetCode06 {
    public static void main(String[] args) {
        System.out.println(new Solution().convert("LEETCODEISHIRING", 4));
        System.out.println(new Solution2().convert("LEETCODEISHIRING", 4));
        System.out.println(new Solution3().convert("LEETCODEISHIRING", 4));
        System.out.println(new Solution4().convert("LEETCODEISHIRING", 4));
    }

    /*
    利用二维矩阵模拟
     */
    static class Solution {
        public String convert(String s, int numRows) {
            int n = s.length(), r = numRows;
            if (r == 1 || r >= n) {
                return s;
            }
            int t = r * 2 - 2;
            int c = (n + t - 1) / t * (r - 1);
            char[][] mat = new char[r][c];
            for (int i = 0, x = 0, y = 0; i < n; ++i) {
                mat[x][y] = s.charAt(i);
                if (i % t < r - 1) {
                    ++x; // 向下移动
                } else {
                    --x;
                    ++y; // 向右上移动
                }
            }
            StringBuffer ans = new StringBuffer();
            for (char[] row : mat) {
                for (char ch : row) {
                    if (ch != 0) {
                        ans.append(ch);
                    }
                }
            }
            return ans.toString();
        }
    }

    /*
    方法二：压缩矩阵空间
     */
    static class Solution2 {
        public String convert(String s, int numRows) {
            int n = s.length(), r = numRows;
            if (r == 1 || r >= n) {
                return s;
            }
            StringBuffer[] mat = new StringBuffer[r];
            for (int i = 0; i < r; ++i) {
                mat[i] = new StringBuffer();
            }
            for (int i = 0, x = 0, t = r * 2 - 2; i < n; ++i) {
                mat[x].append(s.charAt(i));
                if (i % t < r - 1) {
                    ++x;
                } else {
                    --x;
                }
            }
            StringBuffer ans = new StringBuffer();
            for (StringBuffer row : mat) {
                ans.append(row);
            }
            return ans.toString();
        }
    }

    static class Solution3 {
        /*
         * 官方方法3，直接构造
         * 根据方法2可知，一个字符只要直到要放到哪一行即可
         * 假设原字符串字符的下标为idx
         * 周期t = r+(r -2) = 2r-2（解法一已有解释）
         * 可以发现：
         * 对于idx mod t < r的，就会放到第(idx mod t)行，
         * 对于idx mod t > r(小于t)的，就会放到第t - (idx mod t)行
         * 有了这个规律，就可以直接按公式算出要放的行，不用像方法2那样求行了，
         * 只要从前往后依次计算该字符的行，然后直接加到每行的末尾，即可达到效果
         */
        public String convert(String s, int numRows) {
            //特殊情况
            //numRows为1，直接返回s
            if (numRows == 1) {
                return s;
            }
            //s长度 <= numRows，直接返回s
            if (s.length() <= numRows) {
                return s;
            }

            int n = s.length();
            //行数为numRows，简写为r。计算所需的列数，为(n/t向上取整)⋅(r−1)
            // Z 字形变换的周期 t=r+(r−2)=2r−2
            int t = numRows + (numRows - 2);

            //定义数组，每行用StringBuilder
            StringBuilder[] matrix = new StringBuilder[numRows];
            for (int i = 0; i < matrix.length; i++) {
                matrix[i] = new StringBuilder();
            }

            //填入每个字符
            for (int i = 0; i < n; i++) {
                //计算要放的行
                int targetRow;
                int mod = i % t;
                if (mod < numRows) {
                    //对于idx mod t < r的，就会放到第(idx mod t)行
                    targetRow = mod;
                } else {
                    //对于idx mod t >= r(小于t)的，就会放到第t - (idx mod t)行
                    targetRow = t - mod;
                }
                //放到对应行
                matrix[targetRow].append(s.charAt(i));
            }

            //输出结果字符串
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                result.append(matrix[i]);
            }
            return result.toString();
        }
    }

    /*
    flag使用
    字符串s是以Z字形为顺序存储的字符串，目标是按行打印。
    设numRows 行字符串分别为 s1,s2,...,sn,则容易发现：按顺序遍历字符串s时，每个字符c在Z字形中对应的行索引先从s1增大之sn,再从sn减小至s1...如此反复。
    因此，解决方案为：模拟这个行索引的变化，在遍历s中把每个字符填到正确的行res[i]

    算法流程：按顺序遍历字符串s;
    1. res[i] += c  : 把每个字符c填入对应的si;
    2. i += flag   : 更新当前字符c对应的行索引；
    3. flag = -flag  : 在达到Z字形转折点时，执行反向；
     */
    static class Solution4 {
        public String convert(String s, int numRows) {
            if (numRows < 2) return s;
            List<StringBuilder> rows = new ArrayList<StringBuilder>();
            for (int i = 0; i < numRows; i++) rows.add(new StringBuilder());
            int i = 0, flag = -1;
            for (char c : s.toCharArray()) {
                rows.get(i).append(c);
                if (i == 0 || i == numRows - 1) flag = -flag;
                i += flag;
            }
            StringBuilder res = new StringBuilder();
            for (StringBuilder row : rows) res.append(row);
            return res.toString();
        }
    }
}
