package com.zhxh.codeproj.designpattern.strategy.better;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/10/30.
 */

public class AttackXL implements IAttackBehavior {
    @Override
    public void attack() {
        Log.e("---", "降龙十八掌");
    }
}
