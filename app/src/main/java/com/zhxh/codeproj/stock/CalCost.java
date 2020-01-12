package com.zhxh.codeproj.stock;

/**
 * Created by zhxh on 2020/1/12
 */
public class CalCost {

    public static void main(String[] args) {

        double currentCost = 7.556;//当前成功
        long currentAmount = 3400;//当前股数

        double transPrice = 6.08;//成交价格
        long transAmount = 1000;//成交股数

        System.out.println("transPrice:" + transPrice + " transAmount:" + transAmount + " calCost:" + calCost(transPrice, transAmount, currentCost, currentAmount));
    }

    private static double calCost(double transPrice, long transAmount, double currentCost, long currentAmount) {
        return (currentCost * currentAmount + getCommission(transPrice, transAmount) + transAmount * transPrice) / (currentAmount + transAmount);
    }

    /**
     * 佣金是指投资者在委托买卖证券成交之后按成交金额的一定比例支付给券商的费用。
     * 此项费用一般由券商的经纪佣金、证券交易所交易经手费及管理机构的证管费等构成。
     * 佣金的收费标准为：不超过成交金额的千分之3，A股起点一般为5元。其中，经手费为成交金额的十万分4.87，证管费为成交金额的十万分之2。
     */
    private static double getCommission(double transPrice, long transAmount) {
        return Math.max(5, transAmount * transPrice * 0.0003);
    }

}
