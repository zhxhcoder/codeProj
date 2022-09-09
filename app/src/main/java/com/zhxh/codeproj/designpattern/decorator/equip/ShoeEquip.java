package com.zhxh.codeproj.designpattern.decorator.equip;

import com.zhxh.codeproj.designpattern.decorator.IEquip;

/**
 * Created by zhxh on 2016/11/1.
 * 鞋子
 * 攻击力: 5
 */

public class ShoeEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战靴子";
    }
}
