package com.zhxh.codeproj.designpattern.strategy.better;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/10/30.
 */

public class DefendTMS implements IDefendBehavior {

    @Override
    public void defend() {
        Log.e("---", "铁布衫");
    }
}
