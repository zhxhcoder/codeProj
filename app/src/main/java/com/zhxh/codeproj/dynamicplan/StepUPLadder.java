package com.zhxh.codeproj.dynamicplan;

/**
 * 12阶的楼梯 一次走一阶或两阶 共有多少种走法
 * <p>
 * <p>
 * 动态规划当中最重要三个的概念
 * 最优子结构|边界|状态转移公式
 * F(1)=1
 * F(2)=2
 * <p>
 * F(n) = F(n-1)+F(n-2)（n>=3）
 */
public class StepUPLadder {
    public static void main(String[] args) {
        System.out.println(getStepWays(12));
    }

    static int getStepWays(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i < n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }
}
