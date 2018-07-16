package com.zhxh.codeproj.dynamicplan;

import java.util.Scanner;

/**
 * 编辑距离，又称Levenshtein距离（也叫做Edit Distance），是指两个字串之间，由一个转成另一个所需的最少编辑操作次数。许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。
 * 例如将kitten一字转成sitting：
 * sitten （k->s）
 * sittin （e->i）
 * sitting （->g）
 * 所以kitten和sitting的编辑距离是3。俄罗斯科学家Vladimir Levenshtein在1965年提出这个概念。
 * 给出两个字符串a,b，求a和b的编辑距离。
 * <p>
 * 状态定义:Fi,j表示第一个字符串的前i个字母和第二个字符串的前j个字母需要编辑的次数，求Fn,m，n和m分别是两个字符串的长度。
 * <p>
 * 状态转移方程：
 * 当Fi,j-1=Fi-1,j时，Fi,j=Fi,j-1；
 * 当Fi,j-1！=Fi-1,j时，Fi,j=min{Fi-1,j-1,Fi,j-1,Fi-1,j}+1.
 */
public class EditLength {

    public static void main(String[] args) {
        System.out.println("输入第一个字符串并回车");
        Scanner in = new Scanner(System.in);
        String aStr = in.nextLine();
        System.out.println("输入第二个字符串并回车");

        String bStr = in.nextLine();
        int aLen = aStr.length();
        int bLen = bStr.length();
        int[][] dp = new int[aLen + 1][bLen + 1];
        for (int i = 0; i < aLen + 1; i++) dp[i][0] = i;
        for (int i = 0; i < bLen + 1; i++) dp[0][i] = i;
        for (int i = 1; i < aLen + 1; i++)
            for (int j = 1; j < bLen + 1; j++)
                if (aStr.charAt(i - 1) == bStr.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
        System.out.println(dp[aLen][bLen]);
    }
}
