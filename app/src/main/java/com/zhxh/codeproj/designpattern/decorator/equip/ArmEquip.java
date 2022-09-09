package com.zhxh.codeproj.designpattern.decorator.equip;

import com.zhxh.codeproj.designpattern.decorator.IEquip;

/**
 * Created by zhxh on 2016/11/1.
 * 武器
 * 攻击力 20
 */

public class ArmEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 20;
    }

    @Override
    public String description() {
        return "屠龙宝刀";
    }
}
