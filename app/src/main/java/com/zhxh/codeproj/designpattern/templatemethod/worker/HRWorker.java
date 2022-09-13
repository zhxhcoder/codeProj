package com.zhxh.codeproj.designpattern.templatemethod.worker;

import com.zhxh.codeproj.designpattern.templatemethod.Worker;
import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/11/2.
 * HR
 */

public class HRWorker extends Worker {

    public HRWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        Log.e("--work", "---" + name + ", 看简历 - 打电话 - 接电话");
    }
}
