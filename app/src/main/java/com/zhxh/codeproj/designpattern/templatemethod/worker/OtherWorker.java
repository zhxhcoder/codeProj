package com.zhxh.codeproj.designpattern.templatemethod.worker;

import com.zhxh.codeproj.designpattern.templatemethod.Worker;
import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/11/2.
 * 一些自由人员
 */

public class OtherWorker extends Worker {

    public OtherWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        Log.e("--work", "---" + name + ",打LOL...");
    }
}
