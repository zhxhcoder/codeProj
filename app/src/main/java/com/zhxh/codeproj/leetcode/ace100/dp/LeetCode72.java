package com.zhxh.codeproj.leetcode.ace100.dp;

/*
72. 编辑距离
给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符


示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2：

输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')


提示：

0 <= word1.length, word2.length <= 500
word1 和 word2 由小写英文字母组成
 */
public class LeetCode72 {
    public static void main(String[] args) {
        System.out.println(new Solution().minDistance("orddse", "xx"));
        System.out.println(new Solution2().minDistance("orddse", "xx"));
        System.out.println(new Solution3().minDistance("orddse", "xx"));
        System.out.println(new Solution4().minDistance("orddse", "xx"));
    }

    /*
     方法1：递归

     模式识别：一旦涉及子问题，可以用自顶向下的递归和自底向上的动态规划

     能否发现子问题
     能否写出递归解
     能否意识到递归解法的局限性
     能否进行优化
     */
    static class Solution {
        /*
        方法一：动态规划
        思路和算法：
        我们可以对任意一个单词进行三种操作：
        插入一个字符；
        删除一个字符；
        替换一个字符。
         */
        public int minDistance(String word1, String word2) {
            int n = word1.length();
            int m = word2.length();
            // 有一个字符串为空串
            if (n * m == 0)
                return n + m;
            // DP 数组
            int[][] D = new int[n + 1][m + 1];
            // 边界状态初始化
            for (int i = 0; i < n + 1; i++) {
                D[i][0] = i;
            }
            for (int j = 0; j < m + 1; j++) {
                D[0][j] = j;
            }
            // 计算所有 DP 值
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    int left = D[i - 1][j] + 1;//删除操作
                    int down = D[i][j - 1] + 1;//增加操作
                    int left_down = D[i - 1][j - 1];//替换操作
                    if (word1.charAt(i - 1) != word2.charAt(j - 1))
                        left_down += 1;
                    //D[i][j]=
                    D[i][j] = Math.min(left, Math.min(down, left_down));
                }
            }
            return D[n][m];
        }
    }

    /*
    动态规划
     */
    static class Solution2 {
        public int minDistance(String word1, String word2) {
            //dp[i][j]表示word1前 i 个字母 转化到 word2 前 j 个需要的最少操作数

            int n1 = word1.length() + 1;
            int n2 = word2.length() + 1;

            int[][] dp = new int[n1][n2];

            //dp[i][0] 给第一列赋值,删除操作，从word1的字符删除到0个字符
            for (int i = 0; i < n1; i++) {
                dp[i][0] = i;
            }

            //dp[0][j] 给第一行赋值，增加操作，从0个字符增加到word2的字符
            for (int j = 0; j < n2; j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i < n1; i++) {
                for (int j = 1; j < n2; j++) {
                    // 删除操作：dp[i - 1][j]
                    // 增加操作：dp[i][j - 1]
                    // 替换操作：dp[i - 1][j - 1]
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);

                    //两个字母一样这个很关键，一定要加上去啊！！！
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                    }
                }
            }
            return dp[n1 - 1][n2 - 1];
        }
    }

    /*
    官方答案
    方法一：动态规划

    我们用 dp[i][j]表示A的前i个字母和B的前j个字母的编辑距离。
     */
    static class Solution3 {
        public int minDistance(String word1, String word2) {
            int n = word1.length();
            int m = word2.length();
            // 有一个字符串为空串
            if (n * m == 0) {
                return n + m;
            }
            // DP 数组
            int[][] dp = new int[n + 1][m + 1];
            // 边界状态初始化
            for (int i = 0; i < n + 1; i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j < m + 1; j++) {
                dp[0][j] = j;
            }
            // 计算所有 DP 值
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    int left = dp[i - 1][j] + 1;
                    int down = dp[i][j - 1] + 1;
                    int left_down = dp[i - 1][j - 1];
                    if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                        left_down += 1;
                    }
                    dp[i][j] = Math.min(left, Math.min(down, left_down));
                }
            }
            return dp[n][m];
        }
    }

    /*
        EditLength类
        System.out.println("输入第一个字符串并回车");
        Scanner in = new Scanner(System.in);
        String aStr = in.nextLine();
        System.out.println("输入第二个字符串并回车");
        String bStr = in.nextLine();
        int aLen = aStr.length();
        int bLen = bStr.length();

        从左下角填表

        E
        S
        R
        O
        H
          R  O  S

     */
    static class Solution4 {
        public int minDistance(String word1, String word2) {
            int aLen = word1.length();
            int bLen = word2.length();
            int[][] dp = new int[aLen + 1][bLen + 1];
            for (int i = 0; i < aLen + 1; i++) dp[i][0] = i;
            for (int i = 0; i < bLen + 1; i++) dp[0][i] = i;
            for (int i = 1; i < aLen + 1; i++)
                for (int j = 1; j < bLen + 1; j++) {
                    int left = dp[i - 1][j];
                    int down = dp[i][j - 1];
                    int left_down = dp[i - 1][j - 1];
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = left_down;
                    } else {
                        dp[i][j] = Math.min(left_down, Math.min(left, down)) + 1;
                    }
                }
            return dp[aLen][bLen];
        }
    }
}
