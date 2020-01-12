package com.zhxh.codeproj.stock;

/**
 * Created by zhxh on 2020/1/12
 */
public class CalCost {

    /**
     * 手续费一般分为三块：佣金、过户费和印花税。
     * 其中佣金和过户费买卖都收取。印花税为单边，向卖方计收。
     */


    private static double transFee = 0.00003;//过户费
    private static double stampTax = 0.00003;//印花税
    private static double Commission = 0.00003;//佣金

    public static void main(String[] args) {

        double currentCost = 7.556;
        double currentPrice = 6.08;
        long currentAmount = 3400;//当前股数

        System.out.println("a=" + currentCost + " b=" + currentCost);
    }

    private static double calCost(double currentCost, double currentPrice, long currentAmount) {
        return 0;
    }


}
