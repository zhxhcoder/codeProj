package com.zhxh.codeproj.designpattern.templatemethod.worker;

import com.zhxh.codeproj.designpattern.templatemethod.Worker;
import com.zhxh.codeproj.Log;

/**
 * Created by zhxh on 2016/11/2.
 * CTO
 */

public class CTOWorker extends Worker {

    public CTOWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        Log.e("--work", "---" + name + ", 开会 - 出API - 检查进度");
    }
}
