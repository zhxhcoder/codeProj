package com.zhxh.codeproj.designpattern.strategy.better;

import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/10/30.
 */

public class RunJCTQ implements IRunBehavior {

    @Override
    public void run() {
        Log.e("---", "金蝉脱壳");
    }
}
